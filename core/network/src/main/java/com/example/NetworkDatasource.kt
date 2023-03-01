package com.example

import com.example.model.NetworkCategories
import com.example.model.NetworkPost
import com.example.retrofit.RetrofitNetworkApi
import javax.inject.Inject

class NetworkDatasource @Inject constructor(
    private val retrofitNetworkApi: RetrofitNetworkApi,
) {
    suspend fun getImages(): NetworkPost {
        return retrofitNetworkApi.getImages()
    }

    suspend fun getCategories(): NetworkCategories  {
        return retrofitNetworkApi.getCategories()
    }
}