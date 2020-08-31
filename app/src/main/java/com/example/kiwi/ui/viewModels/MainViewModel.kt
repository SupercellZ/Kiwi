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


class MainViewModel : ViewModel() {

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
    val todayFlights: LiveData<MutableList<Flight>?>
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

    fun onResume(todayDateActivity: Date) {
        viewModelScope.launch {
            try {
                showLoading()

                if (todayDate == null ||
                    Utils.getDateId(todayDate!!) != Utils.getDateId(todayDateActivity) /*not same day*/
                ) { //retrieve from repo
                    todayDate = todayDateActivity

                    val newFlights = FlightsRepo.getFlights(todayDate!!)
                    _todayFlights.value = newFlights
                } else { //refresh ui, pass same values previously retrieved
                    _todayFlights.value = _todayFlights.value
                }

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