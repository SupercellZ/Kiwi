package com.example.kiwi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    // TODO: Rename and change types of parameters
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

        val view = inflater.inflate(R.layout.fragment_flight, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flightFromTV.text = "${flight.cityFrom}, ${flight.countryFrom}"
        flightToTV.text = "${flight.cityTo}, ${flight.countryTo}"

        dTimeTV.text = flight.departureDate.toString()
        aTimeTV.text = flight.arrivalDate.toString()
        durationTV.text = flight.flightDuration

        priceTV.text = getString(R.string.price_euro, flight.priceInEUR.toString())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FlightFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(flight: Flight) =
            FlightFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(FLIGHT_KEY, flight)
                }
            }
    }
}