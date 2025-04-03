package com.virent.gweather.domain

data class WeatherData(
    val dateTime: Long,
    val offset: Int,
    val weather: WeatherCondition = WeatherCondition.CLEAR,
    val description: String,
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val cloudiness: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDegree: Int,
    val city: String,
    val countryCode: String,
    val sunrise: Long,
    val sunset: Long
)