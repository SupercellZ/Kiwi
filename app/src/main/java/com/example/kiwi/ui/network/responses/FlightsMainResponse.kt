package com.example.kiwi.ui.network.responses

import com.example.kiwi.pojo.Flight
import com.squareup.moshi.Json

data class FlightsMainResponse (
    @field:Json(name = "search_id") val id: String?,
    @field:Json(name = "data") val flights: MutableList<FlightResponse>
) {

    companion object {
        fun toFlights(flightsResponse: List<FlightResponse>) : MutableList<Flight> {
            val flights : MutableList<Flight> = arrayListOf()

            //convert each "FlightResponse" to "Flight"
            flightsResponse.forEach {
                flights.add(Flight(it))
            }

            return flights
        }
    }
}