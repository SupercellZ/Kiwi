package com.example.kiwi

import com.example.kiwi.pojo.Flight
import com.example.kiwi.ui.network.FlightService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class FlightsRepo {

    companion object {

        suspend fun getNewFlights(todayDate: Date): MutableList<Flight> {
            return withContext(Dispatchers.IO) {

                //invoke API
                val flightsResponse = FlightService.create().getFlights()

                //save to database ~ for now not gonna save until we know the criteria we matching against


                //convert FlightResponse to list of Flight
                flightsResponse.toFlights().subList(0, 5)
            }
        }
    }
}