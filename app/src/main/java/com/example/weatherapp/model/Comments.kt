package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName


data class Comments(
    val postId: Int,
    val id: Int,
    val name: String,
    val emil: String,
    @SerializedName("body") val text: String
)
