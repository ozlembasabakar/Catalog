package com.example.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.PostInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PostInfoDao {
    @Query("SELECT * FROM unsplash_image_table ORDER BY random() LIMIT 10")
    fun getAllPostInfos(): Flow<List<PostInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPostInfos(postInfos: List<PostInfo>)
}