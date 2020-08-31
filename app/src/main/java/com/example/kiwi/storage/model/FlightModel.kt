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

        //converts list of FlightModel to list of Flight
        fun toFlights(flightModels: List<FlightModel>) : MutableList<Flight> {
            val results : MutableList<Flight> = arrayListOf()

            flightModels.forEach {
                results.add(Flight(it))
            }

            return results
        }
    }
}