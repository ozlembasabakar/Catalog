package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val id: String,
    val slug: String,
    val title: String,
)