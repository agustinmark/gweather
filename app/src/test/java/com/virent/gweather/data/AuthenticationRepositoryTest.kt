package com.virent.gweather.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.virent.gweather.mock.MockData.EMAIL
import com.virent.gweather.mock.MockData.PASSWORD
import com.virent.gweather.mock.MockData.SIGNIN_ERROR_MESSAGE
import com.virent.gweather.mock.MockData.SIGNUP_ERROR_MESSAGE
import com.virent.gweather.mock.MockData.TEST_EXCEPTION_FAIL_MESSAGE
import com.virent.gweather.mock.MockData.WRONG_PASSWORD
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class AuthenticationRepositoryTest {

    @MockK
    private lateinit var authRemoteDataSource: AuthenticationRemoteDataSource
    @MockK
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var repository: AuthenticationRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { authRemoteDataSource.currentUser } returns firebaseUser
        repository = AuthenticationRepository(authRemoteDataSource)
    }

    @Test
    fun `Delegate Get Current User`() {
        val user = repository.currentUser
        verify { authRemoteDataSource.currentUser }

        assert(firebaseUser == user)
    }

    @Test
    fun `Delegate Sign in`() = runTest {
        coEvery { authRemoteDataSource.signIn(EMAIL, PASSWORD) } just Runs

        repository.signIn(EMAIL, PASSWORD)
        coVerify { authRemoteDataSource.signIn(EMAIL, PASSWORD) }
    }

    @Test
    fun `Delegate Sign in Error`() = runTest {
        coEvery { authRemoteDataSource.signIn(EMAIL, WRONG_PASSWORD) } throws Exception(
            SIGNIN_ERROR_MESSAGE
        )

        try {
            repository.signIn(EMAIL, WRONG_PASSWORD)
            fail(TEST_EXCEPTION_FAIL_MESSAGE)
        } catch (e: Exception) {
            coVerify { authRemoteDataSource.signIn(EMAIL, WRONG_PASSWORD) }
            assertEquals(SIGNIN_ERROR_MESSAGE, e.message)
        }
    }

    @Test
    fun `Delegate Sign up`() = runTest {
        coEvery { authRemoteDataSource.signUp(EMAIL, PASSWORD) } just Runs

        repository.signUp(EMAIL, PASSWORD)
        coVerify { authRemoteDataSource.signUp(EMAIL, PASSWORD) }
    }

    @Test
    fun `Delegate Sign up Error`() = runTest {
        every { authRemoteDataSource.currentUser } returns null
        repository = AuthenticationRepository(authRemoteDataSource)
        coEvery { authRemoteDataSource.signUp(EMAIL, WRONG_PASSWORD) } throws Exception(
            SIGNUP_ERROR_MESSAGE
        )

        try {
            repository.signUp(EMAIL, WRONG_PASSWORD)
            fail(TEST_EXCEPTION_FAIL_MESSAGE)
        } catch (e: Exception) {
            coVerify { authRemoteDataSource.signUp(EMAIL, WRONG_PASSWORD) }
            assertEquals(SIGNUP_ERROR_MESSAGE, e.message)
        }
    }

    @Test
    fun `Delegate Sign out`() {
        every { authRemoteDataSource.signOut() } just Runs

        repository.signOut()
        verify { authRemoteDataSource.signOut() }
    }
}