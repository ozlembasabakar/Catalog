package com.example.retrofit

import com.example.model.NetworkCategories
import com.example.model.NetworkPost
import retrofit2.http.GET

interface RetrofitNetworkApi {

    @GET("END_POINT")
    suspend fun getImages(): NetworkPost

    @GET("END_POINT")
    suspend fun getCategories(): NetworkCategories
}