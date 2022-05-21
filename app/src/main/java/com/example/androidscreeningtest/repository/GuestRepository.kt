package com.example.androidscreeningtest.repository

import androidx.lifecycle.MutableLiveData
import com.example.androidscreeningtest.data.Guest
import com.example.androidscreeningtest.networking.GuestApi
import com.example.androidscreeningtest.networking.Resource
import com.example.androidscreeningtest.networking.ResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** 21/05/2022 Created by: meitaptr */
open class GuestRepository(
    private val guestApi: GuestApi,
    private val responseHandler: ResponseHandler
) {
    var resWeatherData: MutableLiveData<Guest>? = null

    suspend fun getGuest(): MutableLiveData<Guest> {
        GuestApi.create().getGuest(10,1).enqueue(object :
            Callback<Guest> {

            override fun onResponse(call: Call<Guest>, response: Response<Guest>) {
                if (response.body() != null) {
                    resWeatherData?.value = response.body()
                }
            }

            override fun onFailure(call: Call<Guest>, t: Throwable) {

            }

        })
        return resWeatherData!!
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