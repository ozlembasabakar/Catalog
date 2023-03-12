package com.example.repository.category

import com.example.dao.CategoryDao
import com.example.model.*
import com.example.retrofit.RetrofitNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi,
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override suspend fun getCategories(): Flow<List<Category>> {
        val category: Flow<List<Category>> = categoryDao.getCategories().map {
            it.map (CategoryEntity::asExternalModel)
        }

        return category
    }

    override suspend fun getNewCategories(): Result<Unit> {
        return try {
            val networkCategory = networkApi.getCatCategories()
            categoryDao.insertCategories(
                networkCategory.map (NetworkCategory::asEntity)
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}