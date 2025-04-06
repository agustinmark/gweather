package com.virent.gweather.domain

import com.virent.gweather.data.ArchiveEntry
import com.virent.gweather.data.ArchiveRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class InsertArchiveEntryUseCaseTest {
    private val repository = mockk<ArchiveRepository>(relaxed = true)
    private lateinit var useCase: InsertArchiveEntryUseCase

    val EMAIL = "abc@d.com"
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

    @Before
    fun setup() {
        useCase = InsertArchiveEntryUseCaseImpl(repository)
    }

    @Test
    fun `Insert Archive Entry`() = runTest {
        useCase.invoke(EMAIL, WEATHER_DATA)

        coVerify {
            repository.add(
                entry = ARCHIVE_ENTRY
            )
        }
    }

    val ERROR_MESSAGE = "Error Insert"
    @Test
    fun `Test Error Insert`() = runTest {
        coEvery { repository.add(ARCHIVE_ENTRY) } throws Exception(ERROR_MESSAGE)

        try {
            useCase.invoke(EMAIL, WEATHER_DATA)
            fail("Exception should have been thrown")
        } catch (e: Exception) {
            assertEquals(ERROR_MESSAGE, e.message)
        }
    }
}