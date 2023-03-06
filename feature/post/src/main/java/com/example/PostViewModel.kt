package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Post
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

    private val _state = MutableStateFlow(PostViewState())
    val state: StateFlow<PostViewState> = _state.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        fetchCatImagesFromRepository()
    }

    fun fetchCatImagesFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.value = true
            val image = repository.getCatImages()

            _state.update { currentState ->
                currentState.copy(
                    image = image
                )
            }
            _isRefreshing.value = false
        }
    }
}

data class PostViewState(
    val image: List<Post> = emptyList(),
)