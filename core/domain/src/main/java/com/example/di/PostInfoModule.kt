package com.example.di

import com.example.GetPostInfo
import com.example.repository.post.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostInfoModule {

    @Provides
    @Singleton
    fun provideGetPostInfoUseCase(repository: PostRepository): GetPostInfo {
        return GetPostInfo(repository)
    }
}