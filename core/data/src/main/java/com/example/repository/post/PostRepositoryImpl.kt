package com.example.repository.post

import com.example.dao.PostInfoDao
import com.example.model.PostInfoWithCategory
import com.example.retrofit.RetrofitNetworkApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val networkApi: RetrofitNetworkApi,
    private val postInfoDao: PostInfoDao,
) : PostRepository {

    override suspend fun getAllPostsInfoByCategoryFromNetwork(category: String): List<PostInfoWithCategory> {
        val networkPostsInfo = networkApi.getAllPostsInfoByCategory(category).map {
            PostInfoWithCategory(
                id = it.id,
                category = category,
                url = it.urls.small,
                likes = it.likes,
                description = it.alt_description
            )
        }
        postInfoDao.insertAllPostInfoByCategories(networkPostsInfo)
        return networkPostsInfo
    }

    override suspend fun getAllPostsInfoByCategoryFromDatabase(category: String): Flow<List<PostInfoWithCategory>> {
        val postsInfo: Flow<List<PostInfoWithCategory>> =
            postInfoDao.getAllPostInfoByCategories(category)

        return postsInfo
    }

    override suspend fun insertNewPostsInfoByCategory(category: String): Result<Unit> {
        return try {
            val networkPostsInfo = networkApi.getAllPostsInfoByCategory(category).map {
                PostInfoWithCategory(
                    id = it.id,
                    category = category,
                    url = it.urls.small,
                    likes = it.likes,
                    description = it.alt_description
                )
            }
            postInfoDao.insertAllPostInfoByCategories(networkPostsInfo)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}