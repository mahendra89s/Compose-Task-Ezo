package com.example.ezo.domain

import com.example.ezo.model.ItemData
import com.example.ezo.model.Items

interface Repository {
    suspend fun fetchItems() : Items?
}