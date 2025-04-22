package com.virent.gweather.core.network

import com.virent.gweather.core.network.model.Clouds
import com.virent.gweather.core.network.model.Coord
import com.virent.gweather.core.network.model.Main
import com.virent.gweather.core.network.model.Sys
import com.virent.gweather.core.network.model.Weather
import com.virent.gweather.core.network.model.Wind
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CurrentWeatherApiTest {

    private val mockWebServer = MockWebServer()
    private lateinit var currentWeather: CurrentWeatherApi

    @Before
    fun setup() {
        mockWebServer.start()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/data/2.5/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        currentWeather = retrofit.create(CurrentWeatherApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun readJsonFromFile(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return inputStream?.bufferedReader()?.use { it.readText() } ?: ""
    }

    @Test
    fun `Current Weather API Success`() = runTest {
        val mockResponse = MockResponse()
            .setBody(readJsonFromFile("openweather_success.json"))
        mockWebServer.enqueue(mockResponse)

        val response = currentWeather.getCurrentWeather(14.4659, 120.9902)

        val request = mockWebServer.takeRequest()
        assertEquals("/data/2.5/weather", request.path?.substringBefore("?"))
        assertTrue(request.path?.contains("lat=14.4659&lon=120.9902&units=metric") == true)

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
    fun `OpenWeather Current API Invalid API Key`() = runTest {
        val mockResponse = MockResponse()
            .setBody(readJsonFromFile("openweather_apikeyerror.json"))

        mockWebServer.enqueue(mockResponse)

        val response = currentWeather.getCurrentWeather(14.4659, 120.9902, apiKey = "abc")

        val request = mockWebServer.takeRequest()
        assertEquals("/data/2.5/weather", request.path?.substringBefore("?"))
        assertTrue(request.path?.contains("lat=14.4659&lon=120.9902&units=metric&appid=abc") == true)

        assertEquals(401, response.cod)
        assertEquals(
            "Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.",
            response.message
        )
    }
}