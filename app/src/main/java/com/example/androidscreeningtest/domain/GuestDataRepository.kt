package com.example.androidscreeningtest.domain

import androidx.lifecycle.MutableLiveData
import com.example.androidscreeningtest.data.cloud.ResGuest
import com.example.androidscreeningtest.domain.GuestApi
import retrofit2.Response

/** 21/05/2022 Created by: meitaptr */
class GuestDataRepository(val guestApi: GuestApi) {
    val TAG = "GuestDataRepository"
    var resGuestData: Response<ResGuest>? = null
    var resGuestDataMutable: MutableLiveData<ResGuest>? = null

//    fun getGuest(): MutableLiveData<ResGuest> {
//        GuestApi.create().getGuest(10,1).enqueue(object :
//            Callback<ResGuest> {
//            override fun onResponse(call: Call<ResGuest>, response: Response<ResGuest>) {
//                if (response.body() != null) {
//                    resWeatherData?.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<ResGuest>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//        return resWeatherData!!
//    }

    init {
        resGuestDataMutable = MutableLiveData<ResGuest>()
    }

    fun getList() = guestApi.getGuest(10,1)

    fun getListOfGuest() {
        GuestApi.create().getGuest(10,1)
    //        .enqueue(object :
//            Callback<ResGuest> {
//            override fun onResponse(call: Call<ResGuest>, response: Response<ResGuest>) {
//                if (response.body() != null) {
//                    resGuestDataMutable?.postValue(response.body())
////                    com.example.androidscreeningtest.Logger.e("guestdata", "${resGuestData!!.body()!!.page}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResGuest>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//        com.example.androidscreeningtest.Logger.e(TAG, "udah return")
//        return resGuestDataMutable
    }

//    suspend fun getGuestRepo(): Resource<Guest> {
//        return try {
//            val response = guestApi.getGuest(10,1)
//            return responseHandler.handleSuccess(response)
//        } catch (e: Exception) {
//            responseHandler.handleException(e)
//        }
//    }
}
