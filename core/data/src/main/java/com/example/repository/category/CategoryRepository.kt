package com.example.repository.category

import com.example.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getAllCategoriesFromDatabase(): Flow<List<Category>>
    suspend fun insertNewCategories(): Result<Unit>
}