package com.example.androidscreeningtest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** 22/05/2022 Created by: meitaptr */
@Entity(tableName = "userTable")
data class UserTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val user_id: Int?,

    @ColumnInfo(name = "user_name")
    val user_name: String?,

    @ColumnInfo(name = "image_url")
    val image_url: String?
)