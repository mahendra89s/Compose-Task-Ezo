package com.example.ezo.domain.usecase

import com.example.ezo.domain.Repository
import com.example.ezo.model.ItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchItemsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun fetchItems() : Flow<List<ItemData>> = flow {
        val data = repository.fetchItems()?.items ?: listOf()
        emit(data)
    }
}