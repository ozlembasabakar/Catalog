package com.example

import com.example.dao.CategoryDao
import com.example.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesPostDao(
        database: PostDatabase,
    ): PostDao = database.postDao()

    @Provides
    fun providesCategoryDao(
        database: CategoryDatabase,
    ): CategoryDao = database.categoryDao()
}
