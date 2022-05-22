package com.example.androidscreeningtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidscreeningtest.presentation.screen4.Screen4ViewModel

/** 21/05/2022 Created by: meitaptr */
class MainViewModelFactory(): ViewModelProvider.Factory{
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(Screen1ViewModel::class.java)){
//            return Screen1ViewModel() as T
//        }
//        throw IllegalArgumentException ("UnknownViewModel")
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Screen4ViewModel::class.java)){
            return Screen4ViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}