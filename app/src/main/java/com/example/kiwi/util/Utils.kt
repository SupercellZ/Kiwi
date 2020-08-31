package com.example.kiwi.util

import java.util.*

class Utils {
    companion object {
        fun getDateId(todayDate: Date): String {
            val calendar = Calendar.getInstance()
            calendar.time = todayDate
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            return "$day-${month}-${year}"
        }
    }
}