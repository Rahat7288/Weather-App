package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Post(
    val userId: Int,

    val title: String,
    @SerializedName("body") val bodyText: String
){
//    primary key will auto increment when we will have any activity
    val id: Int = 0
}