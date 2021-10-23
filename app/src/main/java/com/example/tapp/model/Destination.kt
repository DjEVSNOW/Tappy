package com.example.tapp.model

data class Destination(
    val id : Long,
    val name : String,
    val logoURL : String,
    val priceRub : Int,
    val lengthDays : Int,
    val transferType : TransferType,
    val tags : List<String>
)
