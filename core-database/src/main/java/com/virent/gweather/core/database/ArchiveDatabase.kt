package com.virent.gweather.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.virent.gweather.core.database.dao.ArchiveEntryDao

@Database(entities = [ArchiveEntry::class], version = 1, exportSchema = false)
abstract class ArchiveDatabase : RoomDatabase() {

    abstract fun archiveDao(): ArchiveEntryDao

    companion object {
        const val DB_NAME = "archive_db"
    }
}