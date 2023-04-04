package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_info_with_category")
data class PostInfoWithCategory(
    @PrimaryKey(autoGenerate = false) val id: String,
    val category: String,
    val url: String,
    val likes: Int,
    val description: String?,
)