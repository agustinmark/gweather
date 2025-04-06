package com.virent.gweather.viewmodels

import app.cash.turbine.test
import com.virent.gweather.data.AuthenticationRepository
import com.virent.gweather.mock.MockData.EMAIL
import com.virent.gweather.mock.MockData.PASSWORD
import com.virent.gweather.mock.MockData.WRONG_PASSWORD
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.Test
import kotlin.test.assertEquals

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class SignUpViewModelTest {

    @MockK(relaxed = true)
    private lateinit var authenticationRepository: AuthenticationRepository

    private lateinit var viewModel: SignUpViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockKAnnotations.init(this)
        viewModel = SignUpViewModel(authenticationRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Sign up Success`() = runTest {
        coEvery { authenticationRepository.signUp(EMAIL, PASSWORD) } just runs

        viewModel.uiState.test{
            assertEquals(SignUpUiState.Idle, awaitItem())

            viewModel.signUp(EMAIL, PASSWORD, PASSWORD, {}, {})
            assertEquals(SignUpUiState.Loading, awaitItem())
            advanceUntilIdle()

            coVerify { authenticationRepository.signUp(EMAIL, PASSWORD) }
            assertEquals(SignUpUiState.Authenticated, awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Sign up Error`() = runTest {
        coEvery { authenticationRepository.signUp(EMAIL, WRONG_PASSWORD) } throws Exception(WRONG_PASSWORD)

        viewModel.uiState.test{
            assertEquals(SignUpUiState.Idle, awaitItem())

            viewModel.signUp(EMAIL, WRONG_PASSWORD, WRONG_PASSWORD, {}, {})
            assertEquals(SignUpUiState.Loading, awaitItem())
            advanceUntilIdle()
            coVerify { authenticationRepository.signUp(EMAIL, WRONG_PASSWORD) }

            assertEquals(SignUpUiState.Idle, awaitItem())
        }
    }
}