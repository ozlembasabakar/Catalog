package com.example.repository

import com.example.model.Category
import com.example.model.PostInfoWithCategory
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

    suspend fun getAllCategoriesFromDatabase(): Flow<List<Category>> {
        return categoryRepository.getAllCategoriesFromDatabase()
    }

    suspend fun insertNewCategories(): Result<Unit> {
        return categoryRepository.insertNewCategories()
    }

    suspend fun getAllPostsInfoByCategoryFromNetwork(category: String): List<PostInfoWithCategory> {
        return postRepository.getAllPostsInfoByCategoryFromNetwork(category)
    }

    suspend fun getAllPostsInfoByCategoryFromDatabase(category: String): Flow<List<PostInfoWithCategory>> {
        return postRepository.getAllPostsInfoByCategoryFromDatabase(category)
    }

    suspend fun insertNewPostsInfoByCategory(category: String): Result<Unit> {
        return postRepository.insertNewPostsInfoByCategory(category)
    }
}