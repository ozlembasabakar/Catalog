package com.example.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.model.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAll(): List<PostEntity>
}