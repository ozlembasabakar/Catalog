package com.example

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabasesModule {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE `categories` (`id` TEXT NOT NULL, `slug` TEXT NOT NULL, `title` TEXT NOT NULL, " +
                        "PRIMARY KEY(`id`))"
            )
        }
    }

    @Provides
    @Singleton
    fun providePostDatabase(
        @ApplicationContext context: Context,
    ): PostDatabase = Room.databaseBuilder(
        context,
        PostDatabase::class.java,
        "PostInfoDatabase",
    )
        .fallbackToDestructiveMigration()
        //.allowMainThreadQueries()
        .addMigrations(MIGRATION_1_2)
        .build()
}