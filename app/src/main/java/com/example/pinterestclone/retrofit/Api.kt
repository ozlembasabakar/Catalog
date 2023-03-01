package com.example.pinterestclone.retrofit

import retrofit2.http.GET

const val END_POINT = "categories"

interface Api {

    @GET(END_POINT)
    suspend fun getCatCategories(): List<CatCategory>
}