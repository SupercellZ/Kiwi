package com.example.kiwi.ui.network

import com.example.kiwi.ui.network.responses.FlightsMainResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface FlightService {

    @GET("/flights?v=3&sort=popularity&asc=0&locale=en&daysInDestinationFrom=&daysInDestinationTo=&affilid=&children=0&infants=0&flyFrom=49.2-16.61-250km&to=anywhere&featureName=aggregateResults&dateFrom=06/03/2021&dateTo=06/04/2021&typeFlight=oneway&returnFrom=&returnTo=&one_per_date=0&oneforcity=1&wait_for_refresh=0&adults=1&limit=50&partner=skypicker-android")
    suspend fun getFlights(): FlightsMainResponse

    companion object {
        private const val API_URL = "https://api.skypicker.com/"

        fun create(): FlightService {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(getHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(FlightService::class.java)
        }

        private fun getHttpClient(): OkHttpClient {
            val okHttpBuilder = OkHttpClient.Builder()
            okHttpBuilder.addInterceptor { chain ->
                val requestWithUserAgent = chain.request().newBuilder()
                    .header("User-Agent", "My custom user agent")
                    .build()
                chain.proceed(requestWithUserAgent)
            }
            return okHttpBuilder.build()
        }
    }
}