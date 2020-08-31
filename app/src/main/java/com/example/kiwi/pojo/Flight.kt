package com.example.kiwi.pojo

import com.example.kiwi.ui.network.responses.FlightResponse
import java.io.Serializable
import java.util.*

class Flight(flightResponse: FlightResponse) : Serializable {
    val id = flightResponse.id
    
    val departureDate = Date(flightResponse.dTimeUTC * 1000L)
    val arrivalDate = Date(flightResponse.aTimeUTC * 1000L)

    val timeZone = departureDate.toString().substring(departureDate.toString().indexOf("GMT")).split(" ")[0]

    val cityTo = flightResponse.cityTo
    val countryTo = flightResponse.countryTo.name
    
    val cityFrom = flightResponse.cityFrom
    val countryFrom = flightResponse.countryFrom.name

    val flightDuration  = flightResponse.flightDuration
    
    val priceInEUR = flightResponse.priceInEUR

    val picId = flightResponse.picId
}