package com.example.model

fun NetworkCategory.asEntity() = CategoryEntity(
    id = id,
    name = name
)