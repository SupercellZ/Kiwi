package com.example.kiwi.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Flight")
data class FlightModel(
    @PrimaryKey val id: String,
    val cityTo: String
)