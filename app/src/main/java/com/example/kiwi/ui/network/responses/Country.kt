package com.example.kiwi.ui.network.responses

import com.squareup.moshi.Json

data class Country(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "name") val name: String
)