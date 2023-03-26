package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dao.CategoryDao
import com.example.dao.PostInfoDao
import com.example.model.Category
import com.example.model.PostInfo
import com.example.model.PostInfoWithCategory

@Database(
    entities = [PostInfo::class, Category::class, PostInfoWithCategory::class],
    version = 6,
    exportSchema = true,
)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostInfoDao
    abstract fun categoryDao(): CategoryDao
}