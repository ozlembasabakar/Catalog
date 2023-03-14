package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dao.CategoryDao
import com.example.model.Category


@Database(entities = [Category::class], version = 2)
abstract class CategoryDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}