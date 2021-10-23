package com.example.tapp.data

import com.example.tapp.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Api events
 *
 * @constructor Create empty Api events
 */
class ApiEvents
{
    var onError : ((code : Int, message : String, exception : Exception?) -> Unit)? = null
    var onLoadStart : (() -> Unit)? = null
    var onLoadEnd : (() -> Unit)? = null

}
class ApiRepository {

    var eventHandler : ApiEvents = ApiEvents()

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