package com.example.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)

fun CategoryEntity.asExternalModel() = Category(
    id = id,
    name = name
)