package com.virent.gweather.core.data

import com.virent.gweather.core.database.ArchiveEntry
import kotlinx.coroutines.flow.Flow

interface ArchiveRepository {

    suspend fun add(entry: ArchiveEntry)

    fun getUserArchive(user: String): Flow<List<ArchiveEntry>>

}