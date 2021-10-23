package com.example.tapp.model

import java.io.Serializable
import java.util.*

data class Trip(
    val id : Long,
    val name : String,
    val startDate : Date,
    val endDate : Date,
    val adults : Int,
    val children : Int,
    val transfers : MutableList<Transfer>,
    val accommodations : List<Accommodation>
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (other !is Trip)
            return false
        return id == other.id
    }
}
