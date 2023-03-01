package com.example

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePostDatabase(
        @ApplicationContext context: Context,
    ): PostDatabase = Room.databaseBuilder(
        context,
        PostDatabase::class.java,
        "PinterestCloneApp",
        )
        .build()
}