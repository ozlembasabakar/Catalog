package com.example

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Category
import com.example.model.PostInfoWithCategory
import com.example.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _postState = MutableStateFlow(PostWithCategoryViewState())
    val postState: StateFlow<PostWithCategoryViewState> = _postState.asStateFlow()

    private val _tabsState = MutableStateFlow(TabsViewState())
    val tabsState: StateFlow<TabsViewState> = _tabsState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    val selectedItem = mutableStateOf("Fashion & Beauty")

    init {
        insertNewCategories()
        getAllCategoriesFromDatabase()
    }

    fun insertNewPostsInfoByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.value = true
            repository.insertNewPostsInfoByCategory(category).onSuccess {

            }.onFailure {
                Log.d("ozlem", "insertNewPostsInfoByCategory: $it")
            }
            _isRefreshing.value = false
        }
    }

    fun getAllPostsInfoByCategoryFromNetwork(category: String) {
        viewModelScope.launch {
            repository.getAllPostsInfoByCategoryFromNetwork(category)
        }
    }

    fun getAllPostsInfoByCategoryFromDatabase(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllPostsInfoByCategoryFromDatabase(category).collect {
                _postState.update { currentState ->
                    currentState.copy(
                        info = it
                    )
                }
            }
        }
    }

    private fun getAllCategoriesFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCategoriesFromDatabase().collect {
                _tabsState.update { currentState ->
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

data class PostWithCategoryViewState(
    val info: List<PostInfoWithCategory> = emptyList(),
)