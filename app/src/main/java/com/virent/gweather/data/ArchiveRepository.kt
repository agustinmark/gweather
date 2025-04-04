package com.virent.gweather.data

import kotlinx.coroutines.flow.Flow

interface ArchiveRepository {

    suspend fun add(entry: ArchiveEntry)

    fun getUserArchive(user: String): Flow<List<ArchiveEntry>>

}