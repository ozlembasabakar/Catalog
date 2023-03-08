/*
package com.example

import com.example.model.Category
import com.example.model.Post
import com.example.retrofit.RetrofitNetworkApi
import javax.inject.Inject

class NetworkDatasource @Inject constructor(
    private val retrofitNetworkApi: RetrofitNetworkApi,
) {
    suspend fun getCatCategories(): List<Category> {
        return retrofitNetworkApi.getCatCategories().map {
            it.toCategory()
        }
    }

    suspend fun getCatImages(): List<Post> {
        return retrofitNetworkApi.getCatImages().map {
            it.toPost()
        }
    }
}*/
