package com.example.android.weatherapp.data.repository

import com.example.android.weatherapp.data.mapper.toEntities
import com.example.android.weatherapp.data.network.api.ApiService
import com.example.android.weatherapp.domain.entity.City
import com.example.android.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> {
        return apiService.searchCity(query).toEntities()
    }
}