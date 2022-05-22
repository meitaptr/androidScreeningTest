package com.example.androidscreeningtest.repository

import androidx.lifecycle.MutableLiveData
import com.example.androidscreeningtest.data.Guest
import com.example.androidscreeningtest.data.ResGuest
import com.example.androidscreeningtest.networking.GuestApi
//import com.example.androidscreeningtest.networking.Resource
//import com.example.androidscreeningtest.networking.ResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** 21/05/2022 Created by: meitaptr */
class GuestRepository {
    var resWeatherData: MutableLiveData<ResGuest>? = null

    fun getGuest(): MutableLiveData<ResGuest> {
        GuestApi.create().getGuest(10,1).enqueue(object :
            Callback<ResGuest> {
            override fun onResponse(call: Call<ResGuest>, response: Response<ResGuest>) {
                if (response.body() != null) {
                    resWeatherData?.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResGuest>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return resWeatherData!!
//        val retrofit = GuestApi()
//        val api = retrofit.retro.create(api::class.java)
//
//        try {
//
//            val response = api.get(
//                longLat = longlat,
//                date = date
//            )
//            _data.value = response
//        } catch (e: Exception) {
//
//            _data.value = null
//        }
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
