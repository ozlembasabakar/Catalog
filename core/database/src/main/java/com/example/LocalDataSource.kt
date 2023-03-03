package com.example

import com.example.dao.PostDao
import com.example.model.Post
import com.example.model.PostEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val postDao: PostDao,
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun getAllCatImages(): List<PostEntity> {
        return postDao.getAllCatImages()
    }

    private fun insertAllCatImages(postEntity: PostEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            postDao.insertAllCatImages(postEntity = postEntity)
        }
    }

    fun saveToDb(post: Post) {
        val postEntity = PostEntity(
            id = post.id,
            height = post.height,
            url = post.url,
            width = post.width
        )
        insertAllCatImages(postEntity)
    }
}
