package com.example.tapp.model

import org.joda.time.DateTime
import java.util.*

data class Transfer(
    val id : Int,
    val price : Int,
    val type: TransferType,
    val departure : Date,
    val arrival : Date,
    val documents : List<TravelDocument>,
    val tags : List<String>,
    val isPayed : Boolean = false,
    var originCity : String = "",
    var destCity : String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Transfer)
            return false
        return id == other.id
    }
}

enum class TransferType {
    CAR, PLANE, TRAIN, BUS, FOOT, BIKE
}

data class TravelDocument(
    val id : Int,
    val fileName: String,
    val ownerName : String,
    val url : String
)
