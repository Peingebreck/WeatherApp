package com.example.android.weatherapp.domain.entity

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<Weather>
)
