package com.virent.gweather.core.domain

import com.virent.gweather.core.data.ArchiveRepository
import javax.inject.Inject

interface ClearUserArchiveUseCase {
    suspend operator fun invoke(user: String)
}

class ClearUserArchiveUseCaseImpl @Inject constructor(
    private val repository: ArchiveRepository
) : ClearUserArchiveUseCase {

    override suspend fun invoke(user: String) = repository.clearUserArchive(user)

}