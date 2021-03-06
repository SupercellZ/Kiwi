package com.example.kiwi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kiwi.R
import com.example.kiwi.pojo.Flight
import kotlinx.android.synthetic.main.fragment_flight.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val FLIGHT_KEY = "flight"

/**
 * A simple [Fragment] subclass.
 * Use the [FlightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlightFragment : Fragment() {

    private lateinit var flight: Flight

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            flight = it.getSerializable(FLIGHT_KEY) as Flight
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //region display flight data
        if (flight.cityFrom != null && flight.countryFrom != null)
            flightFromTV.text = "${flight.cityFrom}, ${flight.countryFrom}"

        if (flight.cityTo != null && flight.countryTo != null)
            flightToTV.text = "${flight.cityTo}, ${flight.countryTo}"

        flight.departureDate?.run {
            dTimeTV.text = this.toString()
        }

        flight.arrivalDate?.run {
            aTimeTV.text = this.toString()
        }

        flight.flightDuration?.run {
            durationTV.text = this
        }

        flight.priceInEUR?.run {
            priceTV.text = getString(R.string.price_euro, this.toString())
        }

        Glide.with(this)
            .load("https://images.kiwi.com/photos/600x330/${flight.picId}.jpg")
            .into(picIV)
        //endregion
    }

    companion object {

        @JvmStatic
        fun newInstance(flight: Flight) =
            FlightFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(FLIGHT_KEY, flight)
                }
            }
    }
}