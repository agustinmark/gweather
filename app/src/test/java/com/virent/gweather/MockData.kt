package com.virent.gweather

import com.virent.gweather.core.domain.model.WeatherCondition
import com.virent.gweather.core.domain.model.WeatherData

object MockData {

    const val LATITUDE = 14.4659
    const val LONGITUDE = 120.9902
    const val UNITS = "metric"
    const val EMAIL = "abc@d.com"
    const val PASSWORD = "Abc12345!"
    const val WRONG_PASSWORD = "wrongpassword"

    val WEATHER_DATA = WeatherData(
        dateTime = 1743827949,
        offset = 28800,
        weather = WeatherCondition.CLOUDS,
        description = "broken clouds",
        temp = 32.86,
        feelsLike = 39.86,
        tempMin = 31.14,
        tempMax = 33.0,
        cloudiness = 75,
        humidity = 62,
        windSpeed = 4.12,
        windDegree = 120,
        city = "Sambayanihan People's Village",
        countryCode = "PH",
        sunrise = 1743803336,
        sunset = 1743847698
    )

    const val SIGNIN_ERROR_MESSAGE = "Invalid password"
}