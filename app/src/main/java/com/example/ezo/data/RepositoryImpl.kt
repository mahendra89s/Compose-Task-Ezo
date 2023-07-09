package com.example.ezo.data

import com.example.ezo.domain.Repository
import com.example.ezo.model.Items
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    retrofit : Retrofit
) : Repository {
    val apiService by lazy { retrofit.create(ApiService::class.java) }
    override suspend fun fetchItems() : Items? = kotlin.runCatching {
        return@runCatching apiService.fetchItems()
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()
}