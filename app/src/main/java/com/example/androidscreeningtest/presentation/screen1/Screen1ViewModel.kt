package com.example.androidscreeningtest.presentation.screen1

import android.app.Application
import androidx.lifecycle.*
import com.example.androidscreeningtest.data.Guest
//import com.example.androidscreeningtest.networking.Resource
import com.example.androidscreeningtest.repository.GuestRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.logging.Logger

/** 09/05/2022 Created by: meitaptr */
class Screen1ViewModel() : ViewModel() {
    val TAG = "Screen1ViewModel"
    private val guestRepository = GuestRepository()

    private val guestListMutable = MutableLiveData<List<Guest>>()
    val guestList: LiveData<List<Guest>> = guestListMutable

    var resWeatherData: LiveData<Guest>? = null

    val gg = MutableLiveData<List<Guest>>()

    init {
        com.example.androidscreeningtest.Logger.e(TAG, "iniviewmodel")
    }

    suspend fun getGuestList() {
        com.example.androidscreeningtest.Logger.e(TAG, "getGuestList")
        val responseWeather = guestRepository.getGuest()
        resWeatherData = responseWeather
//        gg.switchMap { gg ->
//            liveData(Dispatchers.IO) {
//                emit(Resource.loading(null))
//                emit(guestRepository.getGuestRepo())
//            }
//        }

//        val response = guestRepository.getGuestRepo()
//        if (response.isSuccessful) {
//            userListMutable.postValue(response.body())
//        }
//        else {
//            onError("error get list user")
//        }
    }

//
//    val errorGetUser = MutableLiveData<String>()
//
//    var job: Job? = null
//    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        onError("Exception handled: ${throwable.localizedMessage}")
//    }
//
//    private fun onError(message: String) {
//        errorGetUser.postValue(message)
//    }
//
//    suspend fun getUserList() {
//        val response = repository.getUser()
//        if (response.isSuccessful) {
//            userListMutable.postValue(response.body())
//        }
//        else {
//            onError("error get list user")
//        }
//    }

}