package com.example

import android.content.Context
import androidx.room.Room
import com.example.database.CategoryDatabase
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

    @Provides
    @Singleton
    fun providePostDatabase(
        @ApplicationContext context: Context,
    ): PostDatabase = Room.databaseBuilder(
        context,
        PostDatabase::class.java,
        "PostDatabase",
        )
        .build()

    @Provides
    @Singleton
    fun provideCategoryDatabase(
        @ApplicationContext context: Context,
    ): CategoryDatabase = Room.databaseBuilder(
        context,
        CategoryDatabase::class.java,
        "CategoryDatabase",
    )
        .build()
}