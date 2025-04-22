package com.virent.gweather.core.domain

import com.virent.gweather.core.data.ArchiveRepository
import com.virent.gweather.core.database.ArchiveEntry
import com.virent.gweather.core.domain.model.WeatherCondition
import com.virent.gweather.core.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetUserArchiveUseCase {
    suspend operator fun invoke(
        user: String
    ): Flow<List<WeatherData>>
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