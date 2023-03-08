package com.example

import com.example.model.Category
import com.example.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke(): Flow<Resource<List<Category>>> {
        return categoryRepository.getCategories()
    }
}