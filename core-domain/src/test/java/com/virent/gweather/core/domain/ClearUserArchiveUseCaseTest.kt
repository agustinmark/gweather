package com.virent.gweather.core.domain

import com.virent.gweather.core.data.ArchiveRepository
import com.virent.gweather.core.domain.MockData.EMAIL
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ClearUserArchiveUseCaseTest {
    private val repository = mockk<ArchiveRepository>()
    private lateinit var useCase: ClearUserArchiveUseCase

    @Before
    fun setup() {
        useCase = ClearUserArchiveUseCaseImpl(repository)
    }

    @Test
    fun `Clear User Archive Success`() = runTest {
        useCase.invoke(EMAIL)
        coVerify { repository.clearUserArchive(EMAIL) }
    }
}