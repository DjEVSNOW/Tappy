package com.example.tapp.model

import org.joda.time.DateTime

data class Transfer(
    val id : Int,
    val price : Int,
    val type: TransferType,
    val departure : DateTime,
    val arrival : DateTime,
    val documents : List<TravelDocument>,
    val tags : List<String>,
)

enum class TransferType {
    CAR, PLANE, TRAIN, BUS, FOOT, BIKE
}

data class TravelDocument(
    val id : Int,
    val fileName: String,
    val ownerName : String,
    val url : String
)
