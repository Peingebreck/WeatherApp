package com.example.android.weatherapp.data.network.api

import com.example.android.weatherapp.data.network.dto.CityDto
import com.example.android.weatherapp.data.network.dto.WeatherCurrentDto
import com.example.android.weatherapp.data.network.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
        @Query("aqi") airQuality: String = "yes"
    ): WeatherCurrentDto

    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("aqi") airQuality: String = "yes",
        @Query("days") daysCount: Int = 4
    ): WeatherForecastDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String
    ): List<CityDto>
}