package com.virent.gweather.viewmodels

import com.virent.gweather.MockData.EMAIL
import com.virent.gweather.MockData.WEATHER_DATA
import com.virent.gweather.core.data.AuthenticationRepository
import com.virent.gweather.core.domain.ClearUserArchiveUseCase
import com.virent.gweather.core.domain.GetUserArchiveUseCase
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.Mockito
import kotlin.test.Test
import kotlin.test.assertEquals

class WeatherArchiveViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val getUserArchiveUseCase = mockk<GetUserArchiveUseCase>()
    private val clearUserArchiveUseCase = mockk<ClearUserArchiveUseCase>()
    private val authRepository = mockk<AuthenticationRepository>()

    private lateinit var viewModel: WeatherArchiveViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery {
            getUserArchiveUseCase.invoke(EMAIL)
        } returns flowOf(listOf(WEATHER_DATA))
        coEvery {
            clearUserArchiveUseCase.invoke(EMAIL)
        } just runs
        coEvery { authRepository.currentUser?.email } returns EMAIL
        viewModel = WeatherArchiveViewModel(
            getUserArchiveUseCase = getUserArchiveUseCase,
            clearUserArchiveUseCase = clearUserArchiveUseCase,
            authRepository = authRepository
        )
    }

    @Test
    fun `Get User Archive`() = runTest {
        testScope.launch {
            viewModel.uiState.collect {
                assertEquals(WeatherArchiveUiState.Loading, it)
            }
            viewModel.retrieveUserArchive(EMAIL)
            Mockito.verify(getUserArchiveUseCase).invoke(EMAIL)
            viewModel.uiState.collect {
                assertEquals(WeatherArchiveUiState.Success(listOf(WEATHER_DATA)), it)
            }
        }
    }

    @Test
    fun `Clear User Archive`() = runTest {
        testScope.launch {
            viewModel.uiState.collect {
                assertEquals(WeatherArchiveUiState.Success(listOf(WEATHER_DATA)), it)
            }
            viewModel.clearUserArchive(EMAIL)
            Mockito.verify(clearUserArchiveUseCase).invoke(EMAIL)
            viewModel.uiState.collect {
                assertEquals(WeatherArchiveUiState.Empty, it)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}