package com.example.model

fun NetworkPost.asEntity() = PostEntity(
    id = id,
    height = height,
    url = url,
    width = width
)