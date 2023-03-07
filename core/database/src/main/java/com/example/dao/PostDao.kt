package com.example.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts LIMIT 50")
    fun getAllCatImages(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCatImages(postEntity: PostEntity)
}