package com.example.repository.post

import com.example.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPostInfo(): Flow<List<Post>>
    suspend fun getNewImages(): Result<Unit>
}