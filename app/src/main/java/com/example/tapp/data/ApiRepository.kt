package com.example.tapp.data

import android.content.SharedPreferences
import android.text.format.DateFormat
import com.example.tapp.model.*
import com.example.tapp.utils.parseDate
import com.example.tapp.utils.parseDateTime
import com.example.tapp.utils.trips
import org.koin.java.KoinJavaComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

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

    private val preferences by KoinJavaComponent.inject(SharedPreferences::class.java)

    suspend fun getMyTrips () : List<Trip> {
        var trips = preferences.trips
        if (trips.size == 0)
        {
            trips = getDummyList()
            preferences.trips = trips
        }
        return trips
    }
    suspend fun saveMyTrips (trips : List<Trip>)  {
        preferences.trips = trips
    }
    fun saveTrip (trip : Trip)  {
        val list = preferences.trips.toMutableList()
        if (!list.contains(trip)) {
            list.add(trip)
        } else {
            list.remove(trip)
            list.add(trip)
        }
        preferences.trips = list
    }

    private fun getDummyList() = listOf(
        Trip(
            0, "В отпуск с девками",
            parseDate("2021-05-01"),
            parseDate("2021-05-15"),
            1,
            0,
            mutableListOf(
                Transfer(
                    0,
                    5000,
                    TransferType.PLANE,
                    parseDateTime("2021-05-01 01:20"),
                    parseDateTime("2021-05-15 15:40"),
                    listOf(
                        TravelDocument(
                            0, "ticket.pdf", "Иванов Иван Иванович", "https://"
                        )
                    ),
                    listOf("Прямой", "Ночной перелёт")
                )
            ),
            listOf(
                Accommodation(
                    0, "Grand Hotel California", Location(0f, 0f), parseDate("2021-05-01"),
                    parseDate("2021-05-15"),
                    listOf("Для взрослых", "Для любовных встреч"),
                    15000
                )
            )
        ),
        Trip(
            0, "22-24 октября, Питер",
            parseDate("2021-10-22"),
            parseDate("2021-10-24"),
            2,
            2,
            mutableListOf(
                Transfer(
                    0, 40000, TransferType.BUS,
                    parseDateTime("2021-05-22 00:00"),
                    parseDateTime("2021-05-22 07:40"),
                    listOf(
                        TravelDocument(0, "ticket.pdf", "Иванов Иван Иванович", "https://"),
                        TravelDocument(1, "ticket.pdf", "Иванов Иван Иванович", "https://"),
                        TravelDocument(2, "ticket.pdf", "Иванов Иван Иванович", "https://"),
                        TravelDocument(3, "ticket.pdf", "Иванов Иван Иванович", "https://"),
                    ),
                    listOf("Прямой")
                ), Transfer(
                    1, 0, TransferType.CAR,
                    parseDateTime("2021-05-24 18:00"),
                    parseDateTime("2021-05-25 03:20"),
                    listOf(),
                    listOf("Гибкий", "Удобно с детьми")
                )
            ),
            listOf(
                Accommodation(
                    0, "Ленинградская", Location(0f, 0f),
                    parseDate("2021-05-22"),
                    parseDate("2021-05-24"),
                    listOf("Близко к центру", "Удобства в номере"),
                    7000
                )
            )
        ),


            )
}