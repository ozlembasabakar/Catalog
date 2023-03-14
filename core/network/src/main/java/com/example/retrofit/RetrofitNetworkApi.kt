package com.example.retrofit

import com.example.model.Category
import com.example.model.PostInfo
import retrofit2.http.GET
import retrofit2.http.Query

const val END_POINT_TOPICS = "topics?client_id=UVw0NbwzCo6hE8moV68b3uLhgWtCNHsgO-hxqr4SU2g"
const val END_POINT_IMAGES_BY_TOPICS =
    "/topics/nature/photos/?client_id=UVw0NbwzCo6hE8moV68b3uLhgWtCNHsgO-hxqr4SU2g"

interface RetrofitNetworkApi {

    @GET(END_POINT_TOPICS)
    suspend fun getCatCategories(): List<Category>

    @GET(END_POINT_IMAGES_BY_TOPICS)
    suspend fun getCatImages(
        @Query("per_page") perPage: Int = 50,
    ): List<PostInfo>
}