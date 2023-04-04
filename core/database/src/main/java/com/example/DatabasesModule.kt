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

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE 'categories' ADD COLUMN 'photos' TEXT"
            )
        }
    }

    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE 'unsplash_image_table' ADD COLUMN 'alt_description' TEXT"
            )
        }
    }

    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE `post_info_with_topics` (`topic` TEXT NOT NULL, `url` TEXT NOT NULL, `likes` INTEGER NOT NULL, `description` TEXT, " +
                        "PRIMARY KEY(`topic`))"
            )
        }
    }

    val MIGRATION_5_6 = object : Migration(5, 6) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP TABLE post_info_with_topics")

            database.execSQL(
                "CREATE TABLE `post_info_with_category` (`id` TEXT NOT NULL, `category` TEXT NOT NULL, `url` TEXT NOT NULL, `likes` INTEGER NOT NULL, `description` TEXT, " +
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
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
        .build()
}