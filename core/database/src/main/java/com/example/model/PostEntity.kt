package com.example.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "posts")
data class PostEntity(
    @ColumnInfo(defaultValue = "")
    val image: String,
    @ColumnInfo(defaultValue = "")
    val category: String,
)