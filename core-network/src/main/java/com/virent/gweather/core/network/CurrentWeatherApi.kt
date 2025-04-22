package com.virent.gweather.core.network

import com.virent.gweather.core.network.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = OpenWeatherMap.API_KEY

    ): CurrentWeatherResponse
}