package com.example

import com.example.model.Post
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkDatasource: NetworkDatasource,
) {

    suspend fun getImages(): Post {
        return Post()
    }

    suspend fun getCategories(): Post {
        return Post()
    }
}
