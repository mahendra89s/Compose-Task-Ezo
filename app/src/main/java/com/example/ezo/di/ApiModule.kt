package com.example.ezo.di

import com.example.ezo.data.RepositoryImpl
import com.example.ezo.domain.Repository
import com.example.ezo.presentation.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gson: Gson
    ) : Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create(gson)
        ).build()
    }

    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor (
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().create()

    @Provides
    fun provideRepository(
        retrofit: Retrofit
    ) : Repository = RepositoryImpl(retrofit)
}