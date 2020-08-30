package com.example.kiwi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kiwi.R
import com.example.kiwi.pojo.Flight

class FlightFragment : Fragment() {

    companion object {
        private val FLIGHT_KEY = "flight"

        fun newInstance(flight: Flight): FlightFragment {
            val args = Bundle()

            args.putSerializable(FLIGHT_KEY, flight)

            val fragment = FlightFragment()
            fragment.arguments = args
            return fragment;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flight, container, false)
    }
}