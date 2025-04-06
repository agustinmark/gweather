package com.virent.gweather.data

import com.virent.gweather.domain.Result
import com.virent.gweather.mock.MockData.LATITUDE
import com.virent.gweather.mock.MockData.LONGITUDE
import com.virent.gweather.mock.MockData.UNITS
import com.virent.gweather.mock.MockData.WEATHER_RESPONSE
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class WeatherRepositoryTest {

    private val currentWeatherApi = mockk<CurrentWeatherApi>()
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        repository = WeatherRepository(currentWeatherApi)
    }

    @Test
    fun `Delegate Get Current Weather`() = runTest {
        coEvery { currentWeatherApi.getCurrentWeather(LATITUDE, LONGITUDE, UNITS) } returns WEATHER_RESPONSE

        val result = repository.getCurrentWeather(LATITUDE, LONGITUDE, UNITS)
        coVerify { currentWeatherApi.getCurrentWeather(LATITUDE, LONGITUDE, UNITS) }

        val response = (result as Result.Success).data
        assertEquals(Coord(120.9902, 14.4659), response.coord)
        assertEquals(
            Weather(
                803, "Clouds", "broken clouds", "04d"

            ), response.weather[0]
        )
        assertEquals("stations", response.base)
        assertEquals(
            Main(
                32.86, 39.86, 31.14, 33.0, 1011, 62
            ), response.main
        )
        assertEquals(10000, response.visibility)
        assertEquals(Wind(4.12, 120), response.wind)
        assertEquals(Clouds(75), response.clouds)
        assertEquals(1743827949, response.dt)
        assertEquals(
            Sys(
                2, 2005706, "PH", 1743803336, 1743847698
            ), response.sys
        )
        assertEquals(28800, response.timezone)
        assertEquals(7729845, response.id)
        assertEquals("Sambayanihan People's Village", response.name)
        assertEquals(200, response.cod)
    }

    @Test
    fun `Delegate Get Current Weather Error`() = runTest {
        coEvery { currentWeatherApi.getCurrentWeather(LATITUDE, LONGITUDE, UNITS) } throws Exception("HTTP 401 Unauthorized")

        val result = repository.getCurrentWeather(LATITUDE, LONGITUDE, UNITS)
        coVerify { currentWeatherApi.getCurrentWeather(LATITUDE, LONGITUDE, UNITS) }

        val response = (result as Result.Error).message
        assertEquals("Error Fetching Data: HTTP 401 Unauthorized", response)
    }
}