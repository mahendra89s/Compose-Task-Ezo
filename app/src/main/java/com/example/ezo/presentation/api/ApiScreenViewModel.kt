package com.example.ezo.presentation.api

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezo.domain.usecase.FetchItemsUseCase
import com.example.ezo.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiScreenViewModel @Inject constructor(
    private val fetchItemsUseCase: FetchItemsUseCase
) : ViewModel() {
    private val _items : MutableStateFlow<ApiScreenUiState> = MutableStateFlow(ApiScreenUiState.Loading)
    val items : StateFlow<ApiScreenUiState> = _items.asStateFlow()

    fun fetchItems(){
        viewModelScope.launch {
            fetchItemsUseCase.fetchItems().collect{
                _items.value = if(it.isNotEmpty()){
                    ApiScreenUiState.Data(it)
                }else {
                    ApiScreenUiState.NoRecord
                }
            }
        }
    }
}