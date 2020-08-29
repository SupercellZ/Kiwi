package com.example.kiwi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


public class MainViewModel : ViewModel() {

//    var x = Date(1601792400  /*dTimeUTC*/ * 1000L).toString()
//    x.substring(20).split(" ")[0]

    val loading : LiveData<Boolean>
        get() = _loading

    private var _loading = MutableLiveData<Boolean>(false)

    fun initialize() {
        _loading.value = true

        viewModelScope.launch {
            delay(5_000)
            _loading.value = false
        }
    }
}