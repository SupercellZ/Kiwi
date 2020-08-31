package com.example.kiwi

import android.app.Application
import com.example.kiwi.di.DaggerMyComponent
import com.example.kiwi.di.MyComponent

class App : Application() {


    companion object {
        lateinit var app: App
        private lateinit var myComponent: MyComponent
    }


    override fun onCreate() {
        super.onCreate()

        myComponent = createMyComponent()
        app = this
    }


    fun getMyComponent(): MyComponent {
        return myComponent
    }

    private fun createMyComponent(): MyComponent {
        return DaggerMyComponent
            .builder()
            .build()
    }
}