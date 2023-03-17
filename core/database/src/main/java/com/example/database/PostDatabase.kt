package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dao.CategoryDao
import com.example.dao.PostInfoDao
import com.example.model.Category
import com.example.model.PostInfo

@Database(
    entities = [PostInfo::class, Category::class],
    version = 2,
    exportSchema = true,
)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostInfoDao
    abstract fun categoryDao(): CategoryDao
}