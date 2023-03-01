package com.example

import com.example.dao.PostDao

class LocalDataSource(
    private val postDao: PostDao,
) {

    fun getAllFromDatabase() {
        //return postDao.getAll()
    }

    fun saveToDb() {}
}
