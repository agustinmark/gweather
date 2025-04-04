package com.virent.gweather.domain

import com.virent.gweather.data.ArchiveEntry
import com.virent.gweather.data.ArchiveRepository
import javax.inject.Inject

interface InsertArchiveEntryUseCase {
    suspend operator fun invoke(
        user: String,
        entry: WeatherData
    )
}

class InsertArchiveEntryUseCaseImpl @Inject constructor(
    private val repository: ArchiveRepository
) : InsertArchiveEntryUseCase {

    override suspend fun invoke(user: String, data: WeatherData) {
        repository.add(
            entry = ArchiveEntry(
                user = user,
                dateTime = data.dateTime,
                offset = data.offset,
                weather = data.weather.name,
                description = data.description,
                temp = data.temp,
                feelsLike = data.feelsLike,
                tempMin = data.tempMin,
                tempMax = data.tempMax,
                cloudiness = data.cloudiness,
                humidity = data.humidity,
                windSpeed = data.windSpeed,
                windDegree = data.windDegree,
                city = data.city,
                countryCode = data.countryCode,
                sunrise = data.sunrise,
                sunset = data.sunset
            )
        )
    }

}