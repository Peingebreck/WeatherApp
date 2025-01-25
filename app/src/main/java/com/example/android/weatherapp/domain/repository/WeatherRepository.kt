package com.example.android.weatherapp.domain.repository

import com.example.android.weatherapp.domain.entity.Forecast
import com.example.android.weatherapp.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast
}