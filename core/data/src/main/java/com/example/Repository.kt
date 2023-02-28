package com.example

import com.example.model.Post
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkDatasource: NetworkDatasource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getImages() {
        saveToDb(
            //networkDatasource.getImages() ya da networkDatasource.getCategories()
        )
        return localDataSource.getAllFromDatabase()
    }

    suspend fun getCategories(): Post {
        return Post()
    }

    fun getFromDatabase() {
        localDataSource.getAllFromDatabase()
    }

    private fun saveToDb() {
        localDataSource.saveToDb()
    }
}
