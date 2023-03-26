package com.example

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.PostInfo
import com.example.model.PostInfoWithCategory
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

    private val _stateWithTopics = MutableStateFlow(PostWithCategoryViewState())
    val stateWithTopics: StateFlow<PostWithCategoryViewState> = _stateWithTopics.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        //fetchCatImagesFromRepository()
    }

    fun fetchCatImagesFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPostInfo().collect {
                _state.update { currentState ->
                    currentState.copy(
                        image = it
                    )
                }
            }
        }
    }

    fun fetchNewImages() {
        viewModelScope.launch {
            _isRefreshing.value = true
            repository.getNewPostInfos().onSuccess {

            }.onFailure {
                Log.d("ozlem", "$it")
            }
            _isRefreshing.value = false
        }
    }

    fun networkCall(topic: String) {
        viewModelScope.launch {
            val networkCall = repository.networkCall(topic)
            _state.update {
                it.copy(
                    image = networkCall
                )
            }
        }
    }

    fun postWithTopics(topic: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val info = repository.postWithTopics(topic)
            _stateWithTopics.update { currentState ->
                currentState.copy(
                    info = info
                )
            }
        }
    }
}

data class PostViewState(
    val image: List<PostInfo> = emptyList(),
)

data class PostWithCategoryViewState(
    val info: List<PostInfoWithCategory> = emptyList(),
)