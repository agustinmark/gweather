package com.virent.gweather.domain

import com.virent.gweather.data.CurrentWeatherResponse
import com.virent.gweather.data.Weather
import com.virent.gweather.data.WeatherRepository
import javax.inject.Inject

interface GetCurrentWeatherUseCase {
    suspend operator fun invoke(
        lat: Double,
        long: Double,
        units: String
    ): Result<WeatherData>
}

class GetCurrentWeatherUseCaseImpl @Inject constructor(
    private val repository: WeatherRepository
) : GetCurrentWeatherUseCase {
    override suspend fun invoke(
        lat: Double,
        long: Double,
        units: String
    ): Result<WeatherData> {
        return when (val result = repository.getCurrentWeather(lat, long, units)) {
            is Result.Success -> {
                val weatherResponse = result.data
                val weatherInfo = map(weatherResponse)
                Result.Success(weatherInfo)
            }
            is Result.Error -> result
        }
    }

    private fun map(response: CurrentWeatherResponse): WeatherData {
        val weather = response.weather.firstOrNull() ?: Weather(0, "", "", "")
        return WeatherData(
            dateTime = response.dt,
            offset = response.timezone,
            weather = WeatherCondition.from(weather.main),
            description = weather.description,
            temp = response.main.temp,
            feelsLike = response.main.feels_like,
            tempMin = response.main.temp_min,
            tempMax = response.main.temp_max,
            cloudiness = response.clouds.all,
            humidity = response.main.humidity,
            windSpeed = response.wind.speed,
            windDegree = response.wind.deg,
            city = response.name,
            countryCode = response.sys.country,
            sunrise = response.sys.sunrise,
            sunset = response.sys.sunset
        )
    }
}