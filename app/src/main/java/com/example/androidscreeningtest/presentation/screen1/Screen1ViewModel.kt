package com.example.androidscreeningtest.presentation.screen1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidscreeningtest.data.UserDatabase
import com.example.androidscreeningtest.data.local.UserTable
import com.example.androidscreeningtest.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** 22/05/2022 Created by: meitaptr */
class Screen1ViewModel(application: Application): AndroidViewModel(application) {
    val repository: UserRepository

    init {
        val dao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepository(dao)
    }

    fun insertEvent(userTable: UserTable) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertUser(userTable) }
}