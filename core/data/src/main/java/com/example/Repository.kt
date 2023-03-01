package com.example

import com.example.model.Category
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
class Repository @Inject constructor(
    private val networkDatasource: NetworkDatasource,
    //private val localDataSource: LocalDataSource,
) {

/*    suspend fun getImages() {
        saveToDb(
            //networkDatasource.getImages() ya da networkDatasource.getCategories()
        )
        return localDataSource.getAllFromDatabase()
    }*/

    suspend fun getCategories(): List<Category> {
        return networkDatasource.getCatCategories().map {
            Category(
                id = it.id,
                name = it.name
            )
        }
    }

/*    fun getFromDatabase() {
        localDataSource.getAllFromDatabase()
    }

    private fun saveToDb() {
        localDataSource.saveToDb()
    }*/
}
