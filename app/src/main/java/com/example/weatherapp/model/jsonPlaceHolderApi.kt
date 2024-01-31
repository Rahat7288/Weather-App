package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface  JsonPlaceHolderApi {
    @GET("posts")
    fun getPost(@Query("userId") userId: Int, @Query("_sort") sort: String, @Query("_order") order: String): Call<MutableList<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<MutableList<Comments>>

    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>

//    another way of post request with the help of

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String,

    ): Call<Post>

}