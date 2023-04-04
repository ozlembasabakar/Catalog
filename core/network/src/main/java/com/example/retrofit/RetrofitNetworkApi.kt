package com.example.retrofit

import com.example.model.Category
import com.example.model.PostInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val END_POINT_ALL_CATEGORIES = "topics?client_id=UVw0NbwzCo6hE8moV68b3uLhgWtCNHsgO-hxqr4SU2g"
const val END_POINT_ALL_PHOTOS_BY_CATEGORIES =
    "/topics/{category}/photos/?client_id=UVw0NbwzCo6hE8moV68b3uLhgWtCNHsgO-hxqr4SU2g"

interface RetrofitNetworkApi {

    @GET(END_POINT_ALL_CATEGORIES)
    suspend fun getAllCategories(
        @Query("per_page") perPage: Int = 10,
    ): List<Category>

    @GET(END_POINT_ALL_PHOTOS_BY_CATEGORIES)
    suspend fun getAllPostsInfoByCategory(
        @Path("category") category: String,
        @Query("per_page") perPage: Int = 20,
    ): List<PostInfo>
}