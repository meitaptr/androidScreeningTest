package com.example.androidscreeningtest.networking

import com.example.androidscreeningtest.Constants.BASE_URL
import com.example.androidscreeningtest.data.Guest
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GuestApi {

    companion object {
//        private var BASEURL = "https://api.openweathermap.org/"

        private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
            this.callTimeout(2, TimeUnit.MINUTES)
            this.connectTimeout(20, TimeUnit.SECONDS)
        }.build()


        fun create(): GuestApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
                .client(client)
                .build()
                .create(GuestApi::class.java)
        }
    }

    @GET("users")
    suspend fun getGuest( @Query("per_page") per_page: Int,
                             @Query("page") page: Int): Call<Guest>
}