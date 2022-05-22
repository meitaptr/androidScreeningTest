package com.example.androidscreeningtest

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import timber.log.Timber

/** 21/05/2022 Created by: meitaptr */
class App: Application() {

    companion object {
        lateinit var instance: App

        @Volatile
        private var context: App? = null
        fun getContext(): App {
            return context ?: synchronized(this){
                App().also {
                    context = it
                }
            }
        }

    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }

    override fun attachBaseContext(base: Context) {
        context = this

        super.attachBaseContext(base)

//        SharedPref.init(base)
    }

//    override fun onCreate() {
//        super.onCreate()
//        Timber.plant(Timber.DebugTree())
////        setupKoin()
//    }
//
//    private fun setupKoin() {
//        startKoin {
//            androidLogger()
//            androidContext(this@App)
//            modules(listOf(
//                networkModule,
//                repositoryModule,
//                viewModelModule
//            ))
//        }
//    }
}