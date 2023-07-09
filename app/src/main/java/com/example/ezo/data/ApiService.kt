package com.example.ezo.data

import com.example.ezo.model.Items
import retrofit2.http.GET

interface ApiService {

    @GET("/kappa/image/task")
    suspend fun fetchItems() : Items
}