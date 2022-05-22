package com.example.androidscreeningtest.data.cloud

import com.google.gson.annotations.SerializedName

/** 21/05/2022 Created by: meitaptr */

data class Guest (
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("avatar")
    val avatar: String,
)