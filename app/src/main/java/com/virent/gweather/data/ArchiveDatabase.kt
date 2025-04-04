package com.virent.gweather.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArchiveEntry::class], version = 1, exportSchema = false)
abstract class ArchiveDatabase : RoomDatabase() {

    abstract fun archiveDao(): ArchiveEntryDao

    companion object {
        const val DB_NAME = "archive_db"
    }
}