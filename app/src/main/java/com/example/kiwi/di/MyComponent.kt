package com.example.kiwi.di

import com.example.kiwi.storage.AppDatabase
import com.example.kiwi.storage.model.FlightModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MyModule::class
    ]
)
interface MyComponent {

    @Singleton
    fun getAppDatabase(): AppDatabase
}