package com.example.model

data class NetworkCategory(
    val id: Int,
    val name: String,
) {
    fun toCategory(): Category {
        return Category(
            id = id,
            name = name
        )
    }
}
