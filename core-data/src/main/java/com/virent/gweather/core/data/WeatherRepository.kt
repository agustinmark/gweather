package com.virent.gweather.core.data

import com.virent.gweather.core.network.CurrentWeatherApi
import com.virent.gweather.core.network.model.CurrentWeatherResponse
import com.virent.gweather.core.network.model.Result
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: CurrentWeatherApi
) {
    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String = "metric"
    ): Result<CurrentWeatherResponse> {
        return try {
            val response = api.getCurrentWeather(lat, lon, units)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Error Fetching Data: ${e.message}")
        }
    }
}