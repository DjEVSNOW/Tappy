package com.example.tapp.data

import com.example.tapp.data.Consts.BASE_URL
import com.example.tapp.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface Api {



    @GET("users/{username}")
    fun getUser(@Path("username") username: String?): Call<User?>?


    @GET("group/{id}/users")
    fun groupList(@Path("id") groupId: Int, @Query("sort") sort: String?): Call<List<User?>?>?


    @POST("users/new")
    fun createUser(@Body user: User?): Call<User?>?

}