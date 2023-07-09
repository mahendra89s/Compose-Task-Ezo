package com.example.ezo.presentation.api

import com.example.ezo.model.ItemData
import com.example.ezo.model.Items

sealed class ApiScreenUiState {
    object Loading : ApiScreenUiState()
    data class Data(
        val data : List<ItemData>
    ) : ApiScreenUiState()
    object NoRecord : ApiScreenUiState()
    object Error : ApiScreenUiState()
}