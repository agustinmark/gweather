package com.virent.gweather.core.data

import com.virent.gweather.core.database.ArchiveEntry
import com.virent.gweather.core.database.dao.ArchiveEntryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineArchiveRepository @Inject constructor(
    private val archiveDao: ArchiveEntryDao
) : ArchiveRepository {

    override suspend fun add(entry: ArchiveEntry) = archiveDao.add(entry)

    override fun getUserArchive(user: String): Flow<List<ArchiveEntry>> =
        archiveDao.getUserArchive(user)

}