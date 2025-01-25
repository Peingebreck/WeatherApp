package com.example.android.weatherapp.domain.repository

import com.example.android.weatherapp.domain.entity.City

interface SearchRepository {

    suspend fun search(query: String): List<City>
}