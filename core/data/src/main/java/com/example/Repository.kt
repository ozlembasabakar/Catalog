package com.example

import com.example.model.Category
import com.example.model.Post
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
class Repository @Inject constructor(
    private val networkDatasource: NetworkDatasource,
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
        return networkDatasource.getCatImages().map {
            Post(
                id = it.id,
                height = it.height,
                url = it.url,
                width = it.width
            )
        }
    }
}
