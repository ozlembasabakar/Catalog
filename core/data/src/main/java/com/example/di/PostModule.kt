package com.example.di

import com.example.PostDatabase
import com.example.repository.post.PostRepository
import com.example.repository.post.PostRepositoryImpl
import com.example.retrofit.RetrofitNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostModule {

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        database: PostDatabase,
        retrofitNetworkApi: RetrofitNetworkApi
    ): PostRepository {
        return PostRepositoryImpl(retrofitNetworkApi, database.postDao())
    }
}