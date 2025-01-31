package com.example.android.weatherapp

import android.app.Application
import com.example.android.weatherapp.di.ApplicationComponent
import com.example.android.weatherapp.di.DaggerApplicationComponent

class WeatherApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}