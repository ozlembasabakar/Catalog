package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dao.PostInfoDao
import com.example.model.PostInfo

@Database(entities = [PostInfo::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostInfoDao
}