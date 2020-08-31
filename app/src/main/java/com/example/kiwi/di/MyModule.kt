package com.example.kiwi.di

import android.content.Context
import androidx.room.Room
import com.example.kiwi.App
import com.example.kiwi.storage.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyModule {

    @Provides
    @Singleton
    fun getApplicationContext(): Context = App.app

    @Provides
    @Singleton
    fun provideMyDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "KiwiDB")
            .build()
    }


}