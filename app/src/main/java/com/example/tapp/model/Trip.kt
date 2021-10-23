package com.example.tapp.model

import java.util.*

data class Trip(
    val id : Long,
    val name : String,
    val startDate : Date,
    val endDate : Date,
    val adults : Int,
    val children : Int,
    val transfers : List<Transfer>
)