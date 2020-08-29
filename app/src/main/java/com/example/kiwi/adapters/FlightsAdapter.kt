package com.example.kiwi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kiwi.fragments.FlightFragment
import com.example.kiwi.pojo.Flight

class FlightsAdapter(fragmentManager: FragmentManager, behavior: Int, private val flights : ArrayList<Flight>) :
    FragmentPagerAdapter(fragmentManager, behavior) {

    val MAX_VALUE = 200

    override fun getItem(position: Int): Fragment {
        return FlightFragment.newInstance(flights[position])
    }

    override fun getCount(): Int {
        return flights.size
    }

}