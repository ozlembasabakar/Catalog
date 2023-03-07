package com.example.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "height") val height: Int?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "width") val width: Int?,
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