package com.example.androidscreeningtest

import timber.log.Timber

/** 21/05/2022 Created by: meitaptr */
object Logger {
    fun e(TAG: String, msg: String) {
        Timber.e("$TAG// $msg")
    }

    fun d(TAG: String, msg: String) {
        Timber.d("$TAG// $msg")
    }

    fun i(TAG: String, msg: String) {
        Timber.i("$TAG// $msg")
    }
}