package com.example.androidscreeningtest.presentation.screen1

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/** 21/05/2022 Created by: meitaptr */
class Screen1ViewModelFactory constructor(val application: Application): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Screen1ViewModel::class.java)){
            return Screen1ViewModel(application) as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}