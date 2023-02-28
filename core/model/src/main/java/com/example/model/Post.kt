package com.example.model

data class Post(
    val category: List<Category> = emptyList(),
    val image: String = "",
)

data class Category(
    val category: String,
)
