package com.example.androidscreeningtest.presentation.screen4

import androidx.lifecycle.*
import com.example.androidscreeningtest.data.Guest
import com.example.androidscreeningtest.data.ResGuest
import com.example.androidscreeningtest.repository.GuestRepository

/** 09/05/2022 Created by: meitaptr */
class Screen4ViewModel : ViewModel() {
    val TAG = "Screen4ViewModel"
    private val guestRepository = GuestRepository()

    private val guestListMutable = MutableLiveData<List<Guest>>()
    val guestList: LiveData<List<Guest>> = guestListMutable

    var resGuestData: LiveData<Guest>? = null
    var resListGuestData: LiveData<List<Guest>>? = null
    var abc: MutableLiveData<ResGuest>? = null

//    init {
//        com.example.androidscreeningtest.Logger.e(TAG, "iniviewmodel")
//    }

    fun getGuestList() {
        com.example.androidscreeningtest.Logger.e(TAG, "getGuestList")
        val resGuest = guestRepository.getGuest()
//        guestListMutable.value = resGuest
//        val a = resGuest
//        resListGuestData = resGuest
//        resGuestData = resGuest
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