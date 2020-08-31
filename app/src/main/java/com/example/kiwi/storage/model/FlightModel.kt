package com.example.kiwi.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Flight")
data class FlightModel(
    @PrimaryKey(autoGenerate = false) val id: String,
    val date: String,
    val cityTo: String
)