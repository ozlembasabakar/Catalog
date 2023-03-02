package com.example

import com.example.model.Category
import com.example.model.NetworkPost
import com.example.model.Post
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
class Repository @Inject constructor(
    private val networkDatasource: NetworkDatasource,
    private val localDataSource: LocalDataSource,
) {
    suspend fun getCategories(): List<Category> {
        return networkDatasource.getCatCategories().map {
            Category(
                id = it.id,
                name = it.name
            )
        }
    }

    suspend fun getCatImages(): List<Post> {

        networkDatasource.getCatImages().map { post ->
            saveToDb(networkPost = post)
        }

        return localDataSource.getAllCatImages().map {
            Post(
                height = it.height,
                id = it.id,
                url = it.url,
                width = it.width
            )
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

    private fun saveToDb(networkPost: NetworkPost) {
        localDataSource.saveToDb(networkPost)
    }
}
