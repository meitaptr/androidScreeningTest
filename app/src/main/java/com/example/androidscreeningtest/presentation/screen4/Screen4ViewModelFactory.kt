package com.example.androidscreeningtest.presentation.screen4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidscreeningtest.domain.GuestDataRepository

/** 21/05/2022 Created by: meitaptr */
class Screen4ViewModelFactory constructor(private val repository: GuestDataRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Screen4ViewModel::class.java)){
            return Screen4ViewModel(this.repository) as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}