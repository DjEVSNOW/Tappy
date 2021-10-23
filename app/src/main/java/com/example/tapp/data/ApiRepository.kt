package com.example.tapp.data

import com.example.tapp.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Consts.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var apiService = retrofit.create(Api::class.java)

    /*suspend fun getUsers () : List<User> {
        return apiService.groupList(0, "").run {

        }
    }*/
}