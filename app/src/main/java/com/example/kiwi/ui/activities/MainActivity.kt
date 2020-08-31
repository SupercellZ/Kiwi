package com.example.kiwi.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.kiwi.R
import com.example.kiwi.pojo.Flight
import com.example.kiwi.ui.adapters.FlightsAdapter
import com.example.kiwi.ui.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var pagerAdapter: FlightsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupViewModel()
    }

    private fun setupViewModel() {
        //region progress bar visibility
        viewModel.loading.observe(this) {
            progressBar.visibility =
                if (it)
                    View.VISIBLE
                else
                    View.GONE
        }
        //endregion

        //region error visibility
        viewModel.errorLoading.observe(this) {
            if (it) {
                val builder = with(AlertDialog.Builder(this)) {
                    setTitle(getString(com.example.kiwi.R.string.network_problem_title))
                    setMessage(getString(R.string.network_problem_msg))
                    setPositiveButton(R.string.ok, null)
                    setCancelable(false)
                }
                builder.show()
            }
        }
        //endregion

        //region today flights display
        viewModel.todayFlights.observe(this) {
            it?.run {
                setupViewPager(this)
            }
        }
        //endregion
    }

    private fun setupViewPager(flights: MutableList<Flight>) {

        if (this::pagerAdapter.isInitialized) {

            //region display today's flights
            pagerAdapter.setItems(flights)

            viewPager.adapter = pagerAdapter
            //endregion

        } else {

            //region initialize adapter & setup viewPager
            pagerAdapter = FlightsAdapter(
                supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
            pagerAdapter.setItems(flights)

            viewPager.adapter = pagerAdapter

            tabLayout.setupWithViewPager(viewPager, false)
            viewPager.offscreenPageLimit = 10
            //endregion
        }
    }

    override fun onResume() {
        super.onResume()

        //in case date was changed in the device's settings, then invoke viewModel and retrieve today's flights.
        viewModel.onResume(Date())
    }
}