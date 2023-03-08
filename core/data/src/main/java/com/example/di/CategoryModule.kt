package com.example.di

import com.example.*
import com.example.repository.category.CategoryRepository
import com.example.repository.category.CategoryRepositoryImpl
import com.example.retrofit.RetrofitNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CategoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        database: CategoryDatabase,
        retrofitNetworkApi: RetrofitNetworkApi
    ): CategoryRepository {
        return CategoryRepositoryImpl(retrofitNetworkApi, database.categoryDao())
    }
}