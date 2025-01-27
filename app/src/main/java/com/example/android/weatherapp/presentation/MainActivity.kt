package com.example.android.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android.weatherapp.data.network.api.ApiFactory
import com.example.android.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiFactory.apiService

        CoroutineScope(Dispatchers.Main).launch {
            val currentWeather = apiService.loadCurrentWeather("Barcelona")
            val forecast = apiService.loadForecast("Barcelona")
            val cities = apiService.searchCity("Barcelona")
            Log.d("MainActivity",
                "Current weather: $currentWeather\n" +
                        "Forecast weather: $forecast\n" +
                        "Cities: $cities"
                )
        }

        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {

            }
        }
    }
}