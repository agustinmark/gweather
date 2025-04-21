package com.virent.gweather.data

import android.util.Log
import com.virent.gweather.domain.Result
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: CurrentWeatherApi
) {
    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String = "metric"
    ): Result<CurrentWeatherResponse> {
        Log.e("Kiiro", "Fetching weather for coordinates: $lat, $lon")
        return try {
            val response = api.getCurrentWeather(lat, lon, units)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Error Fetching Data: ${e.message}")
        }
    }
}