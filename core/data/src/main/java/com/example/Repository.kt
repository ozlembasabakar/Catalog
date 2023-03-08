package com.example

import com.example.model.Category
import com.example.model.Post
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

    fun getPostInfo(): Flow<List<Post>> {
        return postRepository.getPostInfo()
    }

    suspend fun getNewImages(): Result<Unit> {
        return postRepository.getNewImages()
    }

    suspend fun getCategoriesFromRetrofit(): Result<Unit> {
        return categoryRepository.getNewCategories()
    }
}