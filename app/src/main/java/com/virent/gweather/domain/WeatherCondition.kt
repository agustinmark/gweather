package com.virent.gweather.domain

enum class WeatherCondition {
    THUNDERSTORM,
    DRIZZLE,
    RAIN,
    SNOW,
    ATMOSPHERE,
    CLEAR,
    CLOUDS;

    companion object {
        fun from(weather: String): WeatherCondition {
            return when (weather) {
                "Thunderstorm" -> WeatherCondition.THUNDERSTORM
                "Drizzle" -> WeatherCondition.DRIZZLE
                "Rain" -> WeatherCondition.RAIN
                "Snow" -> WeatherCondition.SNOW
                "Atmosphere" -> WeatherCondition.ATMOSPHERE
                "Clear" -> WeatherCondition.CLEAR
                "Clouds" -> WeatherCondition.CLOUDS
                else -> WeatherCondition.CLOUDS
            }
        }
    }
}