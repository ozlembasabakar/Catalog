package com.example

import com.example.model.NetworkCategory
import com.example.retrofit.RetrofitNetworkApi
import javax.inject.Inject

class NetworkDatasource @Inject constructor(
    private val retrofitNetworkApi: RetrofitNetworkApi,
) {
    suspend fun getCatCategories(): List<NetworkCategory> {
        return retrofitNetworkApi.getCatCategories()
    }

    //suspend fun getCatImages(): List<Cat> {
      //  return api.getCatImages()
    //}
}