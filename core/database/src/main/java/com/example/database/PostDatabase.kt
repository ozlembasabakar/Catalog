package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dao.PostDao
import com.example.model.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}