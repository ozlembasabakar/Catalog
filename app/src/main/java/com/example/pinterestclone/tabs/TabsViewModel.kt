package com.example.pinterestclone.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.GetCategories
import com.example.Resource
import com.example.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabsViewModel @Inject constructor(
    private val getCategories: GetCategories,
) : ViewModel() {

    private val _state = MutableStateFlow(TabsViewState())
    val state: StateFlow<TabsViewState> = _state.asStateFlow()

    init {
        fetchCategoryDataFromRepository()
    }

    private fun fetchCategoryDataFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            val category = getCategories

            category()
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.update { currentState ->
                                currentState.copy(
                                    category = result.data ?: emptyList()
                                )
                            }
                        }
                        is Resource.Error -> {
                            _state.update { currentState ->
                                currentState.copy(
                                    category = result.data ?: emptyList()
                                )
                            }
                        }
                    }
                }.launchIn(this)
        }
    }
}

data class TabsViewState(
    val category: List<Category> = emptyList(),
)