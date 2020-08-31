package com.example.kiwi.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.kiwi.pojo.Flight
import com.example.kiwi.ui.fragments.FlightFragment

class FlightsAdapter(fragmentManager: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fragmentManager, behavior) {

    private val fragments: MutableList<FlightFragment> = arrayListOf()

    override fun getItem(position: Int): Fragment {
//        return FlightFragment.newInstance(flights[position])
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

//    override fun getItemPosition(wtf: Any): Int {
//        val indexOf = fragments.indexOf(wtf)
//        return if (indexOf >= 0)
//            PagerAdapter.POSITION_UNCHANGED
//        else
//            PagerAdapter.POSITION_NONE
//    }

    fun clear() {
        fragments.clear()
        notifyDataSetChanged()
    }

    fun setItems(todayFlights: List<Flight>) {

        fragments.clear()
        for (todayFlight in todayFlights) {
            fragments.add(FlightFragment.newInstance(todayFlight))
        }

        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        fragments.removeAt(position)
        notifyDataSetChanged()
    }

}