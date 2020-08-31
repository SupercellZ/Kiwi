package com.example.kiwi.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kiwi.pojo.Flight
import com.example.kiwi.ui.network.responses.FlightResponse
import com.example.kiwi.util.Utils

@Entity(tableName = "Flight")
data class FlightModel(
    @PrimaryKey(autoGenerate = false) val id: String,
    val dateId: String,

//    @field:Json(name = "dTimeUTC") val dTimeUTC: Long,
//    @field:Json(name = "aTimeUTC") val aTimeUTC: Long,
//
//    @field:Json(name = "cityTo") val cityTo: String,
//    @field:Json(name = "countryTo") val countryTo: Country,
//
//    @field:Json(name = "cityFrom") val cityFrom: String,
//    @field:Json(name = "countryFrom") val countryFrom: Country,
//
//    @field:Json(name = "fly_duration") val flightDuration: String,
//
//    @field:Json(name = "price") val priceInEUR: Int,
//
//    @field:Json(name = "mapIdto") val picId: String,
//
//    @field:Json(name = "flyFrom") val flyFrom: String,
//    @field:Json(name = "flyTo") val flyTo: String

    val departureTime: Long,
    val arrivalTime: Long,

    val cityTo: String,
    val countryTo: String,

    val cityFrom: String,
    val countryFrom: String,

    val flightDuration: String,

    val price: Int,

    val picId: String,

    val flyFrom: String,
    val flyTo: String
) {
    companion object {
        fun toFlights(flightModels: List<FlightModel>) : MutableList<Flight> {
            val results : MutableList<Flight> = arrayListOf()

            flightModels.forEach {
                results.add(Flight(it))
            }

            return results
        }
    }
}