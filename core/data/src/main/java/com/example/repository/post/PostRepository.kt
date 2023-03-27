package com.example.repository.post

import com.example.model.PostInfo
import com.example.model.PostInfoWithCategory
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPostInfo(): Flow<List<PostInfo>>
    suspend fun getNewPostInfos(): Result<Unit>
    suspend fun networkCall(topic: String): List<PostInfoWithCategory>
    suspend fun getPostWithTopics(topic: String): Flow<List<PostInfoWithCategory>>
    suspend fun getNewPostWithTopics(category: String): Result<Unit>
}