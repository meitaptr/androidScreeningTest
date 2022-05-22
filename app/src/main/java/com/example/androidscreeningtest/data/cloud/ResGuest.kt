package com.example.androidscreeningtest.data.cloud

import com.example.androidscreeningtest.data.cloud.Guest
import com.google.gson.annotations.SerializedName

/** 22/05/2022 Created by: meitaptr */
data class ResGuest (
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val listGuest: List<Guest>
    )