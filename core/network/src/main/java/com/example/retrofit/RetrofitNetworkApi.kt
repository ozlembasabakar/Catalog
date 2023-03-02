package com.example.retrofit

import com.example.model.NetworkCategory
import com.example.model.NetworkPost
import retrofit2.http.GET

const val END_POINT_CATEGORIES = "categories"
const val END_POINT = "images/search?limit=50&api_key=live_z259ntj4viM8MgW9w8hPcvOEEWRHaEPnIeTgVWJ0G1RDn1Xg3Tte6GpNI7Qa12ch"

interface RetrofitNetworkApi {

    @GET(END_POINT_CATEGORIES)
    suspend fun getCatCategories(): List<NetworkCategory>

    @GET(END_POINT)
    suspend fun getCatImages(): List<NetworkPost>
}