package com.virent.gweather.data

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
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
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class AuthenticationRemoteDataSourceTest {

    @MockK
    private lateinit var auth: FirebaseAuth

    @MockK
    private lateinit var authResult: AuthResult

    @MockK
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var dataSource: AuthenticationRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { auth.currentUser } returns firebaseUser
        dataSource = AuthenticationRemoteDataSource(auth)
    }

    @Test
    fun `Get Current User`() {
        val user = dataSource.currentUser
        verify { auth.currentUser }

        assert(firebaseUser == user)
    }

    @Test
    fun `Sign In Success`() = runTest {
        val task = mockk<Task<AuthResult>>()
        coEvery { auth.signInWithEmailAndPassword(EMAIL, PASSWORD) } returns task
        mockTaskSuccess(task, authResult)

        dataSource.signIn(EMAIL, PASSWORD)
        coVerify { auth.signInWithEmailAndPassword(EMAIL, PASSWORD) }
    }

    @Test
    fun `Sign In Error`() = runTest {
        val task = mockk<Task<AuthResult>>()
        coEvery { auth.signInWithEmailAndPassword(EMAIL, PASSWORD) } returns task
        mockTaskFailure(task, Exception(SIGNIN_ERROR_MESSAGE))

        try {
            dataSource.signIn(EMAIL, PASSWORD)
            fail(TEST_EXCEPTION_FAIL_MESSAGE)
        } catch (e: Exception) {
            coVerify { auth.signInWithEmailAndPassword(EMAIL, PASSWORD) }
            assertEquals(SIGNIN_ERROR_MESSAGE, e.message)
        }
    }

    @Test
    fun `Sign Up Success`() = runTest {
        val task = mockk<Task<AuthResult>>()
        coEvery { auth.createUserWithEmailAndPassword(EMAIL, PASSWORD) } returns task
        mockTaskSuccess(task, authResult)

        dataSource.signUp(EMAIL, PASSWORD)
        coVerify { auth.createUserWithEmailAndPassword(EMAIL, PASSWORD) }
    }

    @Test
    fun `Sign Up Error`() = runTest {
        val task = mockk<Task<AuthResult>>()
        coEvery { auth.createUserWithEmailAndPassword(EMAIL, WRONG_PASSWORD) } returns task
        mockTaskFailure(task, Exception(SIGNUP_ERROR_MESSAGE))

        try {
            dataSource.signUp(EMAIL, WRONG_PASSWORD)
            fail(TEST_EXCEPTION_FAIL_MESSAGE)
        } catch (e: Exception) {
            coVerify { auth.createUserWithEmailAndPassword(EMAIL, WRONG_PASSWORD) }
            assertEquals(SIGNUP_ERROR_MESSAGE, e.message)
        }
    }

    private fun <T> mockTaskSuccess(task: Task<T>, result: T) {
        val slot = slot<OnCompleteListener<T>>()

        every { task.isComplete } returns true
        every { task.isSuccessful } returns true
        every { task.result } returns result
        every { task.exception } returns null
        every { task.isCanceled } returns false

        every { task.addOnCompleteListener(capture(slot)) } answers {
            slot.captured.onComplete(task)
            task
        }
    }

    private fun <T> mockTaskFailure(task: Task<T>, exception: Exception) {
        val slot = slot<OnCompleteListener<T>>()

        every { task.isComplete } returns true
        every { task.isSuccessful } returns false
        every { task.exception } returns exception
        every { task.isCanceled } returns false

        every { task.addOnCompleteListener(capture(slot)) } answers {
            slot.captured.onComplete(task)
            task
        }
    }
}