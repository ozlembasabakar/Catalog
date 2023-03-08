package com.example.repository.category

import com.example.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<List<Category>>
    suspend fun getNewCategories(): Result<Unit>
}