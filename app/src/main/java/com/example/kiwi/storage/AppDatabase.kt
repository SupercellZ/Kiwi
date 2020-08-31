package com.example.kiwi.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kiwi.storage.dao.FlightDAO
import com.example.kiwi.storage.model.FlightModel

@Database(
    entities = [
        FlightModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFlightDAO(): FlightDAO
}