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

    override suspend fun getCategories(): Flow<List<Category>> {
        val category: Flow<List<Category>> = categoryDao.getCategories()

        return category
    }

    override suspend fun getNewCategories(): Result<Unit> {
        return try {
            val networkCategory = networkApi.getCatCategories()
            categoryDao.insertCategories(networkCategory)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}