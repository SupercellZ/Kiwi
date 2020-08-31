package com.example.kiwi.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kiwi.FlightsRepo
import com.example.kiwi.pojo.Flight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


public class MainViewModel : ViewModel() {

//    var x = Date(1601792400  /*dTimeUTC*/ * 1000L).toString()
//    x.substring(20).split(" ")[0]

    //region loading LiveData
    val loading: LiveData<Boolean>
        get() = _loading
    private var _loading = MutableLiveData<Boolean>(false)
    //endregion

    //region error LiveData
    val errorLoading: LiveData<Boolean>
        get() = _errorLoading
    private var _errorLoading = MutableLiveData<Boolean>(false)
    //endregion

    //region flights LiveData
    val todayFlights: LiveData<MutableList<Flight>>
        get() = _todayFlights
    private var _todayFlights = MutableLiveData<MutableList<Flight>>()
    //endregion

    private var todayDate: Date = Date()

    private fun showLoading() {
        _loading.value = true;
    }

    private fun hideLoading() {
        _loading.value = false;
    }

    fun onResume() {
        viewModelScope.launch {
            try {
                showLoading()

                withContext(Dispatchers.IO) {
//                    delay(3_000)
                }

                val newFlights = FlightsRepo.getNewFlights(todayDate)

                _todayFlights.value = newFlights

//                var x = Date(body.flights[0].dTimeUTC  /*dTimeUTC*/ * 1000L).toString()
//                x.substring(20).split(" ")[0]
            } catch (e: Exception) {
                e.printStackTrace()
                _errorLoading.value = true
                _errorLoading.value = false
            } finally {
                hideLoading()
            }
        }
    }

}