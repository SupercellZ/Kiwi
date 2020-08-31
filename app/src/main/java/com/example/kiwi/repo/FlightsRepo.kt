package com.example.kiwi.repo

import com.example.kiwi.App
import com.example.kiwi.pojo.Flight
import com.example.kiwi.storage.model.FlightModel
import com.example.kiwi.ui.network.FlightService
import com.example.kiwi.ui.network.responses.FlightsMainResponse
import com.example.kiwi.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class FlightsRepo {

    companion object {

        private const val max_results = 5


        /**
         * Get Flights either from database or from API , based on Today's date and if flights were previously saved.
         */
        suspend fun getFlights(todayDate: Date): MutableList<Flight> {
            return withContext(Dispatchers.IO) {

                val results: MutableList<Flight> = arrayListOf()

                val todayDateID = Utils.getDateId(todayDate)

                val database = App.app.getMyComponent().getAppDatabase()
                val flightDAO = database.getFlightDAO()

                //get saved flights in database
                val allPreviousFlights = flightDAO.getAllFlights()
                val todayFlights = flightDAO.getAllFlights(todayDateID)

                if (todayFlights.isEmpty()) { //didn't fetch anything yet for today

                    val flights1 = FlightService.create().getFlights()
                    val flights = flights1.flights

                    //region today is a new day
                    //get previous cities
                    val previousCities = allPreviousFlights.map { it -> it.cityTo }

                    //filter response by removing whatever has any previous city
                    val filtered =
                        if (previousCities.isEmpty()) //no previous cities, all flights are valid
                            flights
                        else //filter out cities already fetched before
                            flights.filter { flightResponse ->
                                !previousCities.contains(
                                    flightResponse.cityTo
                                )
                            }

                    //we have filtered flights ready to be displayed
                    if (filtered.isEmpty().not()) {
                        val subList = filtered.subList(0, max_results.coerceAtMost(filtered.size))
                        results.addAll(FlightsMainResponse.toFlights(subList))

                        //region save to database
                        val dbModels = subList.map { flight ->
                            FlightModel(
                                flight.id,
                                todayDateID,
                                flight.dTimeUTC,
                                flight.aTimeUTC,
                                flight.cityTo,
                                flight.countryTo.name,
                                flight.cityFrom,
                                flight.countryFrom.name,
                                flight.flightDuration,
                                flight.priceInEUR,
                                flight.picId,
                                flight.flyFrom,
                                flight.flyTo
                            )
                        }
                        flightDAO.insert(dbModels)
                        //endregion
                    }
                    //endregion

                } else { //use the saved flights in database

                    results.addAll(FlightModel.toFlights(todayFlights))

                }

                results
            }
        }
    }
}