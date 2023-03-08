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
        //emit(Resource.Loading())

        val postsInfo: Flow<List<Post>> = postDao.getAllCatImages().map {
            it.map {
                it.toPost()
            }
        }
        return postsInfo
        /*
        emit(Resource.Success(postsInfo))

        try {
            val remotePostsInfo = networkApi.getCatImages()
            postDao.insertAllCatImages(remotePostsInfo.map {
                PostEntity(
                    height = it.height,
                    id = it.id,
                    url = it.url,
                    width = it.width
                )
            })
            emit(Resource.Success(remotePostsInfo.map { it.toPost() }))
        } catch(e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = postsInfo
                )
            )
        } catch(e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = postsInfo
                )
            )
        }

        //val newPostInfos = postDao.getAllCatImages().map { it.toPost() }
*/
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