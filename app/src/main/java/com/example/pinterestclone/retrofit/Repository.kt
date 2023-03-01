package com.example.pinterestclone.retrofit

import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api,
) {

    suspend fun getCatCategories(): List<CatCategory> {
        return api.getCatCategories()
    }
}