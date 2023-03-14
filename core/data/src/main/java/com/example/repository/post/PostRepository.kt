package com.example.repository.post

import com.example.model.PostInfo
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPostInfo(): Flow<List<PostInfo>>
    suspend fun getNewPostInfos(): Result<Unit>
}