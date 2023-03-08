package com.example.repository.category

import com.example.Resource
import com.example.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<Resource<List<Category>>>
}