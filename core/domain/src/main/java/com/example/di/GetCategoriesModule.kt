package com.example.di

import com.example.*
import com.example.repository.category.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GetCategoriesModule {

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(repository: CategoryRepository): GetCategories {
        return GetCategories(repository)
    }
}