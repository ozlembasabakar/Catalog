package com.example.pinterestclone.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(CatViewState(emptyList()))
    val state: StateFlow<CatViewState> = _state.asStateFlow()

    init {
        fetchDataFromRepository()
    }

    private fun fetchDataFromRepository() {
        viewModelScope.launch {
            val catCategory = repository.getCatCategories()

            _state.update { currentState ->
                currentState.copy(
                    category = catCategory
                )
            }
        }
    }
}

data class CatViewState(
    val category: List<CatCategory>,
)
