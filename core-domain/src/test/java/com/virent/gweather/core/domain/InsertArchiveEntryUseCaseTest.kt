package com.virent.gweather.core.domain

import com.virent.gweather.core.data.ArchiveRepository
import com.virent.gweather.core.domain.MockData.ARCHIVE_ENTRY
import com.virent.gweather.core.domain.MockData.EMAIL
import com.virent.gweather.core.domain.MockData.WEATHER_DATA
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class InsertArchiveEntryUseCaseTest {
    private val repository = mockk<ArchiveRepository>(relaxed = true)
    private lateinit var useCase: InsertArchiveEntryUseCase

    @Before
    fun setup() {
        useCase = InsertArchiveEntryUseCaseImpl(repository)
    }

    @Test
    fun `Insert Archive Entry`() = runTest {
        useCase.invoke(EMAIL, WEATHER_DATA)

        coVerify {
            repository.add(
                entry = ARCHIVE_ENTRY
            )
        }
    }

    val ERROR_MESSAGE = "Error Insert"

    @Test
    fun `Test Error Insert`() = runTest {
        coEvery { repository.add(ARCHIVE_ENTRY) } throws Exception(ERROR_MESSAGE)

        try {
            useCase.invoke(EMAIL, WEATHER_DATA)
            fail("Exception should have been thrown")
        } catch (e: Exception) {
            assertEquals(ERROR_MESSAGE, e.message)
        }
    }
}