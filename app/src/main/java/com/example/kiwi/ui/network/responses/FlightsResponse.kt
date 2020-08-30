package com.example.kiwi.ui.network.responses

import com.example.kiwi.pojo.Flight
import com.squareup.moshi.Json

data class FlightsResponse (
    @field:Json(name = "search_id") val id: String?,
    @field:Json(name = "data") val flights: List<FlightResponse>
) {
    fun toFlights() : MutableList<Flight> {
        val flights : MutableList<Flight> = arrayListOf()

        //convert each "FlightResponse" to "Flight"
        this.flights.forEach {
            flights.add(Flight(it))
        }

        return flights
    }
}