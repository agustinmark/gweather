package com.virent.gweather.mock

import com.virent.gweather.data.ArchiveEntry
import com.virent.gweather.data.Clouds
import com.virent.gweather.data.Coord
import com.virent.gweather.data.CurrentWeatherResponse
import com.virent.gweather.data.Main
import com.virent.gweather.data.Sys
import com.virent.gweather.data.Weather
import com.virent.gweather.data.Wind
import com.virent.gweather.domain.WeatherCondition
import com.virent.gweather.domain.WeatherData

object MockData {

    const val LATITUDE = 14.4659
    const val LONGITUDE = 120.9902
    const val UNITS = "metric"
    const val EMAIL = "abc@d.com"
    const val PASSWORD = "Abc12345!"
    const val WRONG_PASSWORD = "wrongpassword"

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
        dateTime = WEATHER_DATA.dateTime,
        offset = WEATHER_DATA.offset,
        weather = WEATHER_DATA.weather.name,
        description = WEATHER_DATA.description,
        temp = WEATHER_DATA.temp,
        feelsLike = WEATHER_DATA.feelsLike,
        tempMin = WEATHER_DATA.tempMin,
        tempMax = WEATHER_DATA.tempMax,
        cloudiness = WEATHER_DATA.cloudiness,
        humidity = WEATHER_DATA.humidity,
        windSpeed = WEATHER_DATA.windSpeed,
        windDegree = WEATHER_DATA.windDegree,
        city = WEATHER_DATA.city,
        countryCode = WEATHER_DATA.countryCode,
        sunrise = WEATHER_DATA.sunrise,
        sunset = WEATHER_DATA.sunset
    )

    val ARCHIVE_ENTRY_2 = ArchiveEntry(
        id = 2,
        user = "efg@h.com",
        dateTime = 1743828405,
        offset = 28800,
        weather = "CLOUDS",
        description = "broken clouds",
        temp = 32.48,
        feelsLike = 38.55,
        tempMin = 31.62,
        tempMax = 32.92,
        cloudiness = 75,
        humidity = 61,
        windSpeed = 4.12,
        windDegree = 120,
        city = "Makati City",
        countryCode = "PH",
        sunrise = 1743803321,
        sunset = 1743847687
    )

    val ARCHIVE_ENTRY_3 = ArchiveEntry(
        id = 3,
        user = "abc@d.com",
        dateTime = 1743828856,
        offset = 28800,
        weather = "CLOUDS",
        description = "broken clouds",
        temp = 32.54,
        feelsLike = 39.05,
        tempMin = 30.51,
        tempMax = 32.97,
        cloudiness = 75,
        humidity = 62,
        windSpeed = 4.12,
        windDegree = 120,
        city = "City of Marikina",
        countryCode = "PH",
        sunrise = 1743803306,
        sunset = 1743847677,
    )

    const val FETCH_ERROR_MESSAGE = "Error Fetching Data"
    const val SIGNIN_ERROR_MESSAGE = "Invalid password"
    const val SIGNUP_ERROR_MESSAGE = "Email already used"
    const val TEST_EXCEPTION_FAIL_MESSAGE = "Exception should have been thrown"
}