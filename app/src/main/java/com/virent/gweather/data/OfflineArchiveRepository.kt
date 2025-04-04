package com.virent.gweather.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineArchiveRepository @Inject constructor(
    private val archiveDao: ArchiveEntryDao
) : ArchiveRepository {

    override suspend fun add(entry: ArchiveEntry) = archiveDao.add(entry)

    override fun getUserArchive(user: String): Flow<List<ArchiveEntry>> =
        archiveDao.getUserArchive(user)

}