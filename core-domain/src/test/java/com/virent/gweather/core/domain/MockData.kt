package com.virent.gweather.core.domain

import com.virent.gweather.core.database.ArchiveEntry
import com.virent.gweather.core.domain.model.WeatherCondition
import com.virent.gweather.core.domain.model.WeatherData
import com.virent.gweather.core.network.model.Clouds
import com.virent.gweather.core.network.model.Coord
import com.virent.gweather.core.network.model.CurrentWeatherResponse
import com.virent.gweather.core.network.model.Main
import com.virent.gweather.core.network.model.Sys
import com.virent.gweather.core.network.model.Weather
import com.virent.gweather.core.network.model.Wind

object MockData {

    const val LATITUDE = 14.4659
    const val LONGITUDE = 120.9902
    const val UNITS = "metric"
    const val EMAIL = "abc@d.com"

    val WEATHER_RESPONSE = CurrentWeatherResponse(
        coord = Coord(120.9902, 14.4659),
        weather = listOf(Weather(803, "Clouds", "broken clouds", "")),
        base = "stations",
        main = Main(32.86, 39.86, 31.14, 33.0, 1011, 62),
        visibility = 10000,
        wind = Wind(4.12, 120),
        clouds = Clouds(75),
        dt = 1743827949,
        sys = Sys(2, 2005706, "PH", 1743803336, 1743847698),
        timezone = 28800,
        message = "",
        id = 7729845,
        name = "Sambayanihan People's Village",
        cod = 200
    )

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

    val ARCHIVE_ENTRY = ArchiveEntry(
        user = EMAIL,
        dateTime = 1743827949,
        offset = 28800,
        weather = "CLOUDS",
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

    const val FETCH_ERROR_MESSAGE = "Error Fetching Data"
    const val TEST_EXCEPTION_FAIL_MESSAGE = "Exception should have been thrown"
}