package com.example.repository.category

import com.example.dao.CategoryDao
import com.example.model.Category
import com.example.retrofit.RetrofitNetworkApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi,
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override suspend fun getAllCategoriesFromDatabase(): Flow<List<Category>> {
        val category: Flow<List<Category>> = categoryDao.getAllCategories()

        return category
    }

    override suspend fun insertNewCategories(): Result<Unit> {
        return try {
            val networkCategory = networkApi.getAllCategories()
            categoryDao.insertAllCategories(networkCategory)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}