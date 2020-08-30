package com.example.kiwi.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kiwi.ui.fragments.FlightFragment
import com.example.kiwi.pojo.Flight

class FlightsAdapter(fragmentManager: FragmentManager, behavior: Int, private val flights : MutableList<Flight>) :
    FragmentPagerAdapter(fragmentManager, behavior) {

    val MAX_VALUE = 200

    override fun getItem(position: Int): Fragment {
        return FlightFragment.newInstance(flights[position])
    }

    override fun getCount(): Int {
        return flights.size
    }

    fun setItems(todayFlights: List<Flight>) {
        flights.clear()
        flights.addAll(todayFlights)
        notifyDataSetChanged()
    }

}