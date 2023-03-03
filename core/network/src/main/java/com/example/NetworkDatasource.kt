package com.example

import com.example.model.Category
import com.example.model.Post
import com.example.retrofit.RetrofitNetworkApi
import javax.inject.Inject

class NetworkDatasource @Inject constructor(
    private val retrofitNetworkApi: RetrofitNetworkApi,
) {
    suspend fun getCatCategories(): List<Category> {
        return retrofitNetworkApi.getCatCategories().map {networkCategory ->
            Category(
                id = networkCategory.id,
                name = networkCategory.name
            )
        }
    }

    suspend fun getCatImages(): List<Post> {
        return retrofitNetworkApi.getCatImages().map {networkPost ->
            Post(
                height = networkPost.height,
                id = networkPost.id,
                url = networkPost.url,
                width = networkPost.width
            )
        }
    }
}