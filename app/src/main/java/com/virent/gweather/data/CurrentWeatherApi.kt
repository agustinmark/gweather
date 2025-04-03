package com.virent.gweather.data

import com.virent.gweather.data.OpenWeatherMap.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = API_KEY

    ): CurrentWeatherResponse
}