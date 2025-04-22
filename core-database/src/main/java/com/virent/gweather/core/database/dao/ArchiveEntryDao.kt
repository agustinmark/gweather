package com.virent.gweather.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.virent.gweather.core.database.ArchiveEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface ArchiveEntryDao {
    @Query(
        value =
            "SELECT * " +
                    "FROM archive " +
                    "WHERE user == :user " +
                    "ORDER BY dateTime DESC"
    )
    fun getUserArchive(user: String): Flow<List<ArchiveEntry>>

    @Insert
    suspend fun add(entry: ArchiveEntry)

}