package com.example.dao

import androidx.room.*
import com.example.model.PostInfo
import com.example.model.PostInfoWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface PostInfoDao {
    @Query("SELECT * FROM unsplash_image_table ORDER BY random() LIMIT 10")
    fun getAllPostInfos(): Flow<List<PostInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPostInfos(postInfos: List<PostInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPostInfoWithTopics(
        postInfoWithTopics: List<PostInfoWithCategory>,
    )

    @Query("SELECT * FROM post_info_with_category WHERE category = :category LIMIT 10")
    fun getAllPostInfosWithTopics(
        category: String,
    ): List<PostInfoWithCategory>

    @Query("DELETE FROM post_info_with_category")
    fun deleteAllPostInfosWithTopics()
}