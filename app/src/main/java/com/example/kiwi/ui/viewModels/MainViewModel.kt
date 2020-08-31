package com.example.kiwi.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kiwi.pojo.Flight
import com.example.kiwi.repo.FlightsRepo
import com.example.kiwi.util.Utils
import kotlinx.coroutines.launch
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

    private var todayDate: Date? = null

    private fun showLoading() {
        _loading.value = true;
    }

    private fun hideLoading() {
        _loading.value = false;
    }

    fun onResume(todayDate: Date) {
        viewModelScope.launch {
            try {
                showLoading()

                val thisTodayDate = this@MainViewModel.todayDate

                if (thisTodayDate == null ||
                    Utils.getDateId(thisTodayDate) != Utils.getDateId(todayDate) /*not same day*/
                ) { //retrieve from repo
                    this@MainViewModel.todayDate = todayDate

                    val newFlights = FlightsRepo.getFlights(todayDate)
                    _todayFlights.value = newFlights
                } else //refresh ui, pass same values previously retrieved
                    _todayFlights.value = _todayFlights.value

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