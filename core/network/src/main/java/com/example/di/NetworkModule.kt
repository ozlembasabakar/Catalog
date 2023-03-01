package com.example.di

import com.example.retrofit.RetrofitNetworkApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        val gson = GsonBuilder().create()

        return Retrofit.Builder().baseUrl("BASE_URL")
            .addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build())
    }

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): RetrofitNetworkApi {
        return builder.build().create(RetrofitNetworkApi::class.java)
    }
}