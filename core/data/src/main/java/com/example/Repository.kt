package com.example

import com.example.model.Category
import com.example.model.PostInfo
import com.example.repository.category.CategoryRepository
import com.example.repository.post.PostRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
class Repository @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val postRepository: PostRepository,
) {

    suspend fun getCategoriesFromDatabase(): Flow<List<Category>> {
        return categoryRepository.getCategories()
    }

    suspend fun getPostInfo(): Flow<List<PostInfo>> {
        return postRepository.getPostInfo()
    }

    suspend fun getNewPostInfos(): Result<Unit> {
        return postRepository.getNewPostInfos()
    }

    suspend fun getCategoriesFromRetrofit(): Result<Unit> {
        return categoryRepository.getNewCategories()
    }
}