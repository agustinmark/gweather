package com.virent.gweather.core.data

import com.virent.gweather.core.database.dao.ArchiveEntryDao
import com.virent.gweather.core.data.MockData.ARCHIVE_ENTRY
import com.virent.gweather.core.data.MockData.ARCHIVE_ENTRY_3
import com.virent.gweather.core.data.MockData.EMAIL
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class OfflineArchiveRepositoryTest {

    private val archiveDao = mockk<ArchiveEntryDao>()
    private lateinit var repository: OfflineArchiveRepository

    @Before
    fun setup() {
        repository = OfflineArchiveRepository(archiveDao)
    }

    @Test
    fun `Add Archive Entry`() = runTest {
        coEvery { archiveDao.add(ARCHIVE_ENTRY) } just Runs

        repository.add(ARCHIVE_ENTRY)
        coVerify { archiveDao.add(ARCHIVE_ENTRY) }
    }

    @Test
    fun `Get User Archive`() = runTest {
        coEvery { archiveDao.getUserArchive(EMAIL) } returns flowOf(listOf(ARCHIVE_ENTRY, ARCHIVE_ENTRY_3))

        val result = repository.getUserArchive(EMAIL)
        coVerify { archiveDao.getUserArchive(EMAIL) }

        val response = result.first()
        assertEquals(ARCHIVE_ENTRY, response[0])
        assertEquals(ARCHIVE_ENTRY_3, response[1])
    }

    @Test
    fun `Clear User Archive`() = runTest {
        coEvery { archiveDao.clearUserArchive(EMAIL) } just Runs

        repository.clearUserArchive(EMAIL)
        coVerify { archiveDao.clearUserArchive(EMAIL) }
    }
}