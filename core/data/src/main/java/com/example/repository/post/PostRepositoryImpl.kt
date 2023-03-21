package com.example.repository.post

import com.example.dao.PostInfoDao
import com.example.model.PostInfo
import com.example.retrofit.RetrofitNetworkApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi,
    private val postInfoDao: PostInfoDao,
) : PostRepository {

    override suspend fun getPostInfo(): Flow<List<PostInfo>> {
        val networkImages = networkApi.getCatImages()
        postInfoDao.insertAllPostInfos(networkImages)

        val postsInfo: Flow<List<PostInfo>> = postInfoDao.getAllPostInfos()
        return postsInfo
    }

    override suspend fun getNewPostInfos(): Result<Unit> {
        return try {
            val networkImages = networkApi.getCatImages()
            postInfoDao.insertAllPostInfos(networkImages)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun networkCall(topic: String): List<PostInfo> {
        return networkApi.networkCall(topic)
    }
}