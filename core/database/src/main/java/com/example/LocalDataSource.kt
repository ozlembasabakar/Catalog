package com.example

import com.example.dao.PostDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val postDao: PostDao,
) {

    fun getAllFromDatabase() {
        //return postDao.getAll()
    }

    fun saveToDb() {}
}
