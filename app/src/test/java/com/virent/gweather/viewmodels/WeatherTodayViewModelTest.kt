package com.virent.gweather.viewmodels

import com.virent.gweather.MockData.EMAIL
import com.virent.gweather.MockData.LATITUDE
import com.virent.gweather.MockData.LONGITUDE
import com.virent.gweather.MockData.UNITS
import com.virent.gweather.MockData.WEATHER_DATA
import com.virent.gweather.core.data.AuthenticationRepository
import com.virent.gweather.core.domain.GetCurrentWeatherUseCase
import com.virent.gweather.core.domain.InsertArchiveEntryUseCase
import com.virent.gweather.core.network.model.Result
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery {
            getCurrentWeatherUseCase.invoke(
                LATITUDE,
                LONGITUDE,
                UNITS
            )
        } returns Result.Success(
            WEATHER_DATA
        )
        coEvery { authRepository.currentUser?.email } returns EMAIL
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
            viewModel.fetchWeather(LATITUDE, LONGITUDE)
            Mockito.verify(getCurrentWeatherUseCase).invoke(LATITUDE, LONGITUDE, UNITS)
            viewModel.uiState.collect {
                assertEquals(WeatherTodayUiState.Success(EMAIL, WEATHER_DATA), it)
            }
        }
    }

}