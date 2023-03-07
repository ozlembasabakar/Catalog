package com.example

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dao.CategoryDao
import com.example.model.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 2)
abstract class CategoryDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}