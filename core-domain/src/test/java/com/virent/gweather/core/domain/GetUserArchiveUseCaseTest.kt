package com.virent.gweather.core.domain

import com.virent.gweather.core.data.ArchiveRepository
import com.virent.gweather.core.domain.MockData.ARCHIVE_ENTRY
import com.virent.gweather.core.domain.MockData.EMAIL
import com.virent.gweather.core.domain.MockData.FETCH_ERROR_MESSAGE
import com.virent.gweather.core.domain.MockData.TEST_EXCEPTION_FAIL_MESSAGE
import com.virent.gweather.core.domain.MockData.WEATHER_DATA
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class GetUserArchiveUseCaseTest {
    private val repository = mockk<ArchiveRepository>()
    private lateinit var useCase: GetUserArchiveUseCase

    @Before
    fun setup() {
        useCase = GetUserArchiveUseCaseImpl(repository)
    }

    @Test
    fun `Get User Archive Success`() = runTest {
        coEvery {
            repository.getUserArchive(EMAIL)
        } returns flowOf(listOf(ARCHIVE_ENTRY))

        val result = useCase.invoke(EMAIL)
        coVerify { repository.getUserArchive(EMAIL) }

        result.collect {
            assertEquals(listOf(WEATHER_DATA), it)
        }
    }

    @Test
    fun `Get User Archive Empty Success`() = runTest {
        coEvery {
            repository.getUserArchive(EMAIL)
        } returns flowOf(listOf())

        val result = useCase.invoke(EMAIL)
        coVerify { repository.getUserArchive(EMAIL) }

        result.collect {
            assertEquals(listOf(), it)
        }
    }

    @Test
    fun `Get User Archive Error`() = runTest {
        coEvery {
            repository.getUserArchive(EMAIL)
        } throws Exception(FETCH_ERROR_MESSAGE)

        try {
            useCase.invoke(EMAIL)
            fail(TEST_EXCEPTION_FAIL_MESSAGE)
        } catch (e: Exception) {
            assertEquals(FETCH_ERROR_MESSAGE, e.message)
        }
    }
}