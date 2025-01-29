package com.example.android.weatherapp.data.mapper

import com.example.android.weatherapp.data.network.dto.CityDto
import com.example.android.weatherapp.domain.entity.City

fun CityDto.toEntity(): City = City(id, name, country)

fun List<CityDto>.toEntities(): List<City> = map { it.toEntity() }