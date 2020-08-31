package com.example.kiwi.ui.network.responses

import com.squareup.moshi.Json

data class FlightResponse (
    @field:Json(name = "id") val id: String,

    @field:Json(name = "dTimeUTC") val dTimeUTC: Long,
    @field:Json(name = "aTimeUTC") val aTimeUTC: Long,

    @field:Json(name = "cityTo") val cityTo: String,
    @field:Json(name = "countryTo") val countryTo: Country,

    @field:Json(name = "cityFrom") val cityFrom: String,
    @field:Json(name = "countryFrom") val countryFrom: Country,

    @field:Json(name = "fly_duration") val flightDuration: String,

    @field:Json(name = "price") val priceInEUR: Int,

    @field:Json(name = "mapIdto") val picId: String
)