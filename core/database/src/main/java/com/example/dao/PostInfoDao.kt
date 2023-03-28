package com.example.dao

import androidx.room.*
import com.example.model.PostInfoWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface PostInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPostInfoByCategories(
        postInfoWithCategory: List<PostInfoWithCategory>,
    )

    @Query("SELECT * FROM post_info_with_category WHERE category = :category ORDER BY random() LIMIT 10")
    fun getAllPostInfoByCategories(
        category: String,
    ): Flow<List<PostInfoWithCategory>>

    @Query("DELETE FROM post_info_with_category")
    fun deleteAllPostInfoWithCategories()
}