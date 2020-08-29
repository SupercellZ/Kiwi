package com.example.kiwi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.kiwi.adapters.FlightsAdapter
import com.example.kiwi.pojo.Flight
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.newFixedThreadPoolContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var pagerAdapter: FlightsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

        setupViewPager()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.loading.observe(this) {
            progressBar.visibility =
                if (it)
                    View.VISIBLE
                else
                    View.GONE
        }

        viewModel.initialize() //this is bogus, remove later.
    }

    private fun setupViewPager() {
        val flights : ArrayList<Flight> = arrayListOf();

        for (i in 0..4)
            flights.add(Flight())

        pagerAdapter = FlightsAdapter(supportFragmentManager, FragmentPagerAdapter.POSITION_NONE,
            flights
        )

        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager, false)
        tabLayout.isEnabled = false
    }
}