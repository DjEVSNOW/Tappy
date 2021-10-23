package com.example.tapp.model

data class Destination(
    val id : Long,
    val name : String,
    val logoURL : String,
    val priceRub : Int,
    val lengthDays : Int,
    val transfer : Transfer,
    val tags : List<String>
)
enum class Transfer {
    CAR, PLANE, TRAIN, BUS, FOOT, BIKE
}
