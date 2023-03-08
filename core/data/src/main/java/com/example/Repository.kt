/*
package com.example

import com.example.model.Category
import com.example.model.Post
import kotlinx.coroutines.*
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
class Repository @Inject constructor(
    private val networkDatasource: NetworkDatasource,
    private val localDataSource: LocalDataSource,
) {
    suspend fun getCategories(): List<Category> {

        saveCategoryDataFromNetworkToDb()

        return localDataSource.getCategories().map {
            it.toCategory()
        }
    }

    suspend fun getCatImages(): List<Post> {

        savePostDataFromNetworkToDb()

        return localDataSource.getAllCatImages().map {
            it.toPost()
        }
    }

    private suspend fun savePostDataFromNetworkToDb() {
        networkDatasource.getCatImages().map { post ->
            savePostEntityToDb(post = post)
        }
    }

    private fun savePostEntityToDb(post: Post) {
        localDataSource.savePostEntityToDb(post)
    }

    private suspend fun saveCategoryDataFromNetworkToDb() {
        networkDatasource.getCatCategories().map { category ->
            saveCategoryEntityToDb(category = category)
        }
    }

    private fun saveCategoryEntityToDb(category: Category) {
        localDataSource.saveCategoryEntityToDb(category)
    }
}
*/
