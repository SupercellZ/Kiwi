package com.example.kiwi.pojo

import com.example.kiwi.storage.model.FlightModel
import com.example.kiwi.ui.network.responses.FlightResponse
import java.io.Serializable
import java.util.*

class Flight : Serializable {
    var id: String? = null

    var departureDate: Date? = null
    var arrivalDate: Date? = null

//    var timeZone = departureDate.toString().substring(departureDate.toString().indexOf("GMT")).split(" ")[0]

    var cityTo: String? = null
    var countryTo: String? = null

    var cityFrom: String? = null
    var countryFrom: String? = null

    var flightDuration: String? = null

    var priceInEUR: Int? = null

    var picId: String? = null

    constructor(flightResponse: FlightResponse) {
        id = flightResponse.id
        departureDate = Date(flightResponse.dTimeUTC * 1000L)
        arrivalDate = Date(flightResponse.aTimeUTC * 1000L)

        cityTo = flightResponse.cityTo
        countryTo = flightResponse.countryTo.name

        cityFrom = flightResponse.cityFrom
        countryFrom = flightResponse.countryFrom.name

        flightDuration = flightResponse.flightDuration

        priceInEUR = flightResponse.priceInEUR

        picId = flightResponse.picId
    }

    constructor(flightModel: FlightModel) {
        id = flightModel.id
        departureDate = Date(flightModel.departureTime * 1000L)
        arrivalDate = Date(flightModel.arrivalTime * 1000L)

        cityTo = flightModel.cityTo
        countryTo = flightModel.countryTo

        cityFrom = flightModel.cityFrom
        countryFrom = flightModel.countryFrom

        flightDuration = flightModel.flightDuration

        priceInEUR = flightModel.price

        picId = flightModel.picId
    }
}