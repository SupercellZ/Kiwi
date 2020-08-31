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

        setupViewModel()

//        setupViewPager(arrayListOf())
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

        viewModel.todayFlights.observe(this) {
            setupViewPager(it)
        }
    }

    private fun setupViewPager(arrayList: MutableList<Flight>) {
//        val flights: ArrayList<Flight> = arrayListOf();

//        for (i in 0..4)
//            flights.add(Flight())

        pagerAdapter = FlightsAdapter(
            supportFragmentManager, FragmentPagerAdapter.POSITION_NONE,
            arrayList
        )

        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume(Date())
    }
}