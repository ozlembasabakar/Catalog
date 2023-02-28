package com.example.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    @ColumnInfo(defaultValue = "")
    val image: String,
    @ColumnInfo(defaultValue = "")
    val category: String,
)