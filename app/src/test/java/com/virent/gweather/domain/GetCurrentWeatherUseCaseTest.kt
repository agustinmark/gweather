package com.virent.gweather.domain

import com.virent.gweather.data.WeatherRepository
import com.virent.gweather.mock.MockData.FETCH_ERROR_MESSAGE
import com.virent.gweather.mock.MockData.LATITUDE
import com.virent.gweather.mock.MockData.LONGITUDE
import com.virent.gweather.mock.MockData.UNITS
import com.virent.gweather.mock.MockData.WEATHER_DATA
import com.virent.gweather.mock.MockData.WEATHER_RESPONSE
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class GetCurrentWeatherUseCaseTest {
    private val repository = mockk<WeatherRepository>()
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        useCase = GetCurrentWeatherUseCaseImpl(repository)
    }

    @Test
    fun `Get Current Weather Success`() = runTest {
        coEvery {
            repository.getCurrentWeather(
                LATITUDE,
                LONGITUDE,
                UNITS
            )
        } returns Result.Success(WEATHER_RESPONSE)

        val result = useCase.invoke(LATITUDE, LONGITUDE, UNITS)
        coVerify { repository.getCurrentWeather(LATITUDE, LONGITUDE, UNITS) }

        assertEquals(Result.Success(WEATHER_DATA), result)
    }


    @Test
    fun `Get Current Weather Error`() = runTest {
        coEvery {
            repository.getCurrentWeather(
                LATITUDE,
                LONGITUDE,
                UNITS
            )
        } returns Result.Error(FETCH_ERROR_MESSAGE)

        val result = useCase.invoke(LATITUDE, LONGITUDE, UNITS)
        coVerify { repository.getCurrentWeather(LATITUDE, LONGITUDE, UNITS) }

        assertEquals(Result.Error(FETCH_ERROR_MESSAGE), result)
    }

}