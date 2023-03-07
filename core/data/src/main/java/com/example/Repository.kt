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
        return networkDatasource.getCatCategories()
    }

    suspend fun getCatImages(): List<Post> {

        saveDataFromNetworkToDb()

        return localDataSource.getAllCatImages().map {
            it.toPost()
        }

        /*
        return networkDatasource.getCatImages().map {
            Post(
                id = it.id,
                height = it.height,
                url = it.url,
                width = it.width
            )
        }
        */
    }

    private suspend fun saveDataFromNetworkToDb() {
        networkDatasource.getCatImages().map { post ->
            saveToDb(post = post)
        }
    }

    private fun saveToDb(post: Post) {
        localDataSource.saveToDb(post)
    }
}
