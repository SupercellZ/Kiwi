package com.example.kiwi.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kiwi.storage.model.FlightModel

@Dao
interface FlightDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(flightModels: FlightModel)


    @Query("SELECT * FROM flight")
    fun getPreviousFlights() : List<FlightModel>
}