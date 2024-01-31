package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.http.GET

interface  JsonPlaceHolderApi {
    @GET("posts")
    fun getPost(): Call<MutableList<Post>>
}