package com.example

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class PostViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(PostWithCategoryViewState())
    val state: StateFlow<PostWithCategoryViewState> = _state.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

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
                _state.update { currentState ->
                    currentState.copy(
                        info = it
                    )
                }
            }
        }
    }
}

data class PostWithCategoryViewState(
    val info: List<PostInfoWithCategory> = emptyList(),
)