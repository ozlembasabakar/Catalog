package com.example.repository.post

import com.example.dao.PostDao
import com.example.model.Post
import com.example.model.PostEntity
import com.example.retrofit.RetrofitNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi,
    private val postDao: PostDao,
) : PostRepository {

    override fun getPostInfo(): Flow<List<Post>> {

        val postsInfo: Flow<List<Post>> = postDao.getAllCatImages().map {
            it.map {
                it.toPost()
            }
        }
        return postsInfo
    }

    override suspend fun getNewImages(): Result<Unit> {
        try {
            val networkImages = networkApi.getCatImages()
            postDao.insertAllCatImages(
                networkImages.map {
                    PostEntity(
                        id = it.id,
                        height = it.height,
                        url = it.url,
                        width = it.width
                    )
                }
            )
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}