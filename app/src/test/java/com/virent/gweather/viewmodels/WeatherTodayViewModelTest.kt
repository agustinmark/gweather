package com.virent.gweather.viewmodels

import com.virent.gweather.data.AuthenticationRepository
import com.virent.gweather.domain.GetCurrentWeatherUseCase
import com.virent.gweather.domain.InsertArchiveEntryUseCase
import com.virent.gweather.domain.Result
import com.virent.gweather.domain.WeatherCondition
import com.virent.gweather.domain.WeatherData
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class WeatherTodayViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val getCurrentWeatherUseCase = mockk<GetCurrentWeatherUseCase>()
    private val insertArchiveEntryUseCase = mockk<InsertArchiveEntryUseCase>()
    private val authRepository = mockk<AuthenticationRepository>()

    private lateinit var viewModel: WeatherTodayViewModel

    val email = "abc@d.com"
    val weatherData = WeatherData(
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
    val lat = 14.4659
    val lon = 120.9902
    val units = "metric"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getCurrentWeatherUseCase.invoke(lat, lon, units) } returns Result.Success(
            weatherData
        )
        coEvery { authRepository.currentUser?.email } returns email
        viewModel = WeatherTodayViewModel(
            getCurrentWeatherUseCase,
            insertArchiveEntryUseCase,
            authRepository
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `State Flow Testing`() = runTest {
        testScope.launch {
            viewModel.uiState.collect {
                assertEquals(WeatherTodayUiState.Loading, it)
            }
            viewModel.fetchWeather(lat, lon)
            Mockito.verify(getCurrentWeatherUseCase).invoke(lat, lon, units)
            viewModel.uiState.collect {
                assertEquals(WeatherTodayUiState.Success(email, weatherData), it)
            }
        }
    }

}