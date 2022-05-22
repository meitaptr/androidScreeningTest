package com.example.androidscreeningtest.data

import com.example.androidscreeningtest.data.local.UserDao
import com.example.androidscreeningtest.data.local.UserTable

/** 22/05/2022 Created by: meitaptr */
class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(userTable: UserTable) {
        userDao.insertUser(userTable)
    }

}