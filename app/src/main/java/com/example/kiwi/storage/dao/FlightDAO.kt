package com.example.kiwi.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kiwi.storage.model.FlightModel
import java.util.*

@Dao
interface FlightDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(flightModels: List<FlightModel>)


    @Query("SELECT * FROM flight WHERE dateId = :date")
    fun getAllFlights(date: String) : List<FlightModel>

    @Query("SELECT * FROM flight")
    fun getAllFlights() : List<FlightModel>
}