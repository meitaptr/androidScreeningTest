package com.example.androidscreeningtest.presentation.screen4

import androidx.lifecycle.*
import com.example.androidscreeningtest.data.cloud.Guest
import com.example.androidscreeningtest.data.cloud.ResGuest
import com.example.androidscreeningtest.domain.GuestDataRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** 09/05/2022 Created by: meitaptr */
class Screen4ViewModel( private val repository : GuestDataRepository) : ViewModel() {
    val TAG = "Screen4ViewModel"

    private val guestListMutable = MutableLiveData<ResGuest>()
    val guestList: LiveData<ResGuest> = guestListMutable

    fun getGuestList() {
        com.example.androidscreeningtest.Logger.e(TAG, "getGuestList")
        val response = repository.getList()
        response.enqueue(object : Callback<ResGuest> {
            override fun onResponse(call: Call<ResGuest>, response: Response<ResGuest>) {
                guestListMutable.postValue(response.body())
            }

            override fun onFailure(call: Call<ResGuest>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}

