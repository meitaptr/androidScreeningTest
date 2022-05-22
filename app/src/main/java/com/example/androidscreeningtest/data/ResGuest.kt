package com.example.androidscreeningtest.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/** 22/05/2022 Created by: meitaptr */
@Parcelize
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
    ) : Parcelable