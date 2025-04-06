package com.virent.gweather.viewmodels

import app.cash.turbine.test
import com.google.firebase.auth.FirebaseUser
import com.virent.gweather.data.AuthenticationRepository
import com.virent.gweather.mock.MockData.EMAIL
import com.virent.gweather.mock.MockData.PASSWORD
import com.virent.gweather.mock.MockData.SIGNIN_ERROR_MESSAGE
import com.virent.gweather.mock.MockData.TEST_EXCEPTION_FAIL_MESSAGE
import com.virent.gweather.mock.MockData.WRONG_PASSWORD
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.fail

class SignInViewModelTest {

    @MockK(relaxed = true)
    private lateinit var authenticationRepository: AuthenticationRepository

    private lateinit var viewModel: SignInViewModel

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockKAnnotations.init(this)
        viewModel = SignInViewModel(authenticationRepository)
    }

    @After
    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Login Success`() = runTest {
        coEvery { authenticationRepository.signIn(EMAIL, PASSWORD) } just runs

        viewModel.uiState.test{
            assertEquals(SignInUiState.Idle, awaitItem())

            viewModel.signIn(EMAIL, PASSWORD, {}, {})
            assertEquals(SignInUiState.Loading, awaitItem())
            advanceUntilIdle()
            coVerify { authenticationRepository.signIn(EMAIL, PASSWORD) }

            assertEquals(SignInUiState.Authenticated, awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Login Failure`() = runTest {
        coEvery { authenticationRepository.signIn(EMAIL, WRONG_PASSWORD) } throws Exception(SIGNIN_ERROR_MESSAGE)

        viewModel.uiState.test{
            assertEquals(SignInUiState.Idle, awaitItem())

            viewModel.signIn(EMAIL, WRONG_PASSWORD, {}, {})
            assertEquals(SignInUiState.Loading, awaitItem())
            advanceUntilIdle()
            coVerify { authenticationRepository.signIn(EMAIL, WRONG_PASSWORD) }

            assertEquals(SignInUiState.Idle, awaitItem())
        }
    }
}