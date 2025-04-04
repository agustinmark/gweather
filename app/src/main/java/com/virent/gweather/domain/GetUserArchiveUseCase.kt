package com.virent.gweather.domain

import com.virent.gweather.data.ArchiveEntry
import com.virent.gweather.data.ArchiveRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetUserArchiveUseCase {
    suspend operator fun invoke(
        user: String
    ): Flow<List<WeatherData>> // TODO: Change To Domain Data Class
}

class GetUserArchiveUseCaseImpl @Inject constructor(
    private val repository: ArchiveRepository
) : GetUserArchiveUseCase {

    override suspend fun invoke(user: String): Flow<List<WeatherData>> {
        return repository.getUserArchive(user).map { entries ->
            entries.map { it.toWeatherData() }
        }
    }

    internal fun ArchiveEntry.toWeatherData(): WeatherData {
        return WeatherData(
            dateTime = dateTime,
            offset = offset,
            weather = WeatherCondition.valueOf(weather),
            description = description,
            temp = temp,
            feelsLike = feelsLike,
            tempMin = tempMin,
            tempMax = tempMax,
            cloudiness = cloudiness,
            humidity = humidity,
            windSpeed = windSpeed,
            windDegree = windDegree,
            city = city,
            countryCode = countryCode,
            sunrise = sunrise,
            sunset = sunset
        )
    }
}