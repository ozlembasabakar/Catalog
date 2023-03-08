package com.example.repository.category

import com.example.Resource
import com.example.dao.CategoryDao
import com.example.model.Category
import com.example.model.CategoryEntity
import com.example.retrofit.RetrofitNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi,
    private val categoryDao: CategoryDao
): CategoryRepository {

    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        //emit(Resource.Loading())

        //val category = categoryDao.getCategories().map { it.toCategory() }
        //emit(Resource.Loading(data = category))

        val category = categoryDao.getCategories().map { it.toCategory() }
        emit(Resource.Success(category))

        try {
            val remoteCategory = networkApi.getCatCategories()
            categoryDao.insertCategories(remoteCategory.map {
                CategoryEntity(
                    id = it.id,
                    name = it.name
                )
            })
        } catch(e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = category
                )
            )
        } catch(e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = category
                )
            )
        }
    }
}