package com.example

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(PostViewState())
    val state: StateFlow<PostViewState> = _state.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        fetchCatImagesFromRepository()
    }

    private fun fetchCatImagesFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.value = true
            repository.getPostInfo().collect {
                _state.update { currentState ->
                    currentState.copy(
                        image = it
                    )
                }
                _isRefreshing.value = false
            }
        }
    }

    fun fetchNewImages() {
        viewModelScope.launch {
            _isRefreshing.value = true
            repository.getNewImages().onSuccess {

            }.onFailure {
                Log.d("ozlem", "$it")
            }
            _isRefreshing.value = false
        }
    }
}

data class PostViewState(
    val image: List<Post> = emptyList(),
)