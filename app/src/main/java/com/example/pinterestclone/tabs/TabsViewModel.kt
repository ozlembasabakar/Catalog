package com.example.pinterestclone.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Repository
import com.example.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(TabsViewState())
    val state: StateFlow<TabsViewState> = _state.asStateFlow()

    init {
        fetchCategoryDataFromRepository()
    }

    private fun fetchCategoryDataFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            val category = repository.getCategories()

            _state.update { currentState ->
                currentState.copy(
                    category = category
                )
            }
        }
    }
}

data class TabsViewState(
    val category: List<Category> = emptyList(),
)