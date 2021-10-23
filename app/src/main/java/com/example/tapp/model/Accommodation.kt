package com.example.tapp.model

import java.util.*

data class Accommodation(
    val id : Long,
    val name : String,
    val location : Location,
    val checkIn : Date,
    val checkOut : Date,

    )
