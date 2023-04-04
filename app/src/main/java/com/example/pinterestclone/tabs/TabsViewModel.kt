package com.example.pinterestclone.tabs

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.Repository
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

    val selectedItem = mutableStateOf("Wallpapers")

    init {
        insertNewCategories()
    }

    fun getAllCategoriesFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCategoriesFromDatabase().collect {
                _state.update { currentState ->
                    currentState.copy(
                        category = it
                    )
                }
            }
        }
    }

    private fun insertNewCategories() {
        viewModelScope.launch {
            repository.insertNewCategories().onSuccess {
                getAllCategoriesFromDatabase()
            }.onFailure {
                Log.d("ozlem", "insertNewCategories: $it")
            }
        }
    }
}

data class TabsViewState(
    val category: List<Category> = emptyList(),
)