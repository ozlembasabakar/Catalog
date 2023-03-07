package com.example.model

data class NetworkPost(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
) {
    fun toPost(): Post {
        return Post(
            height = height,
            id = id,
            url = url,
            width = width
        )
    }
}