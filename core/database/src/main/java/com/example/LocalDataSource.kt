package com.example

import com.example.dao.CategoryDao
import com.example.dao.PostDao
import com.example.model.Category
import com.example.model.CategoryEntity
import com.example.model.Post
import com.example.model.PostEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val postDao: PostDao,
    private val categoryDao: CategoryDao
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun getAllCatImages(): List<PostEntity> {
        return postDao.getAllCatImages()
    }

    fun getCategories(): List<CategoryEntity> {
        return categoryDao.getCategories()
    }

    private fun insertAllCatImages(postEntity: PostEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            postDao.insertAllCatImages(postEntity = postEntity)
        }
    }

    private fun inserCategories(categoryEntity: CategoryEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.insertCategories(categoryEntity = categoryEntity)
        }
    }

    fun savePostEntityToDb(post: Post) {
        val postEntity = PostEntity(
            id = post.id,
            height = post.height,
            url = post.url,
            width = post.width
        )
        insertAllCatImages(postEntity)
    }

    fun saveCategoryEntityToDb(category: Category) {
        val categoryEntity = CategoryEntity(
            id = category.id,
            name = category.name
        )
        inserCategories(categoryEntity)
    }
}
