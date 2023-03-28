package com.example.repository.post

import com.example.model.PostInfoWithCategory
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getAllPostsInfoByCategoryFromNetwork(category: String): List<PostInfoWithCategory>
    suspend fun getAllPostsInfoByCategoryFromDatabase(category: String): Flow<List<PostInfoWithCategory>>
    suspend fun insertNewPostsInfoByCategory(category: String): Result<Unit>
}