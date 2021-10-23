package com.example.tapp.model

import org.joda.time.DateTime

data class Transfer(
    val id : Int,
    val type: TransferType,
    val departure : DateTime,
    val arrival : DateTime,
    val documents : List<TravelDocument>
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
