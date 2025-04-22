package com.virent.gweather.core.database.di

import android.content.Context
import androidx.room.Room
import com.virent.gweather.core.database.ArchiveDatabase
import com.virent.gweather.core.database.dao.ArchiveEntryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideArchiveDatabase(
        @ApplicationContext context: Context
    ): ArchiveDatabase {
        return Room.databaseBuilder(
            context,
            ArchiveDatabase::class.java,
            ArchiveDatabase.Companion.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideArchiveEntryDao(
        database: ArchiveDatabase
    ): ArchiveEntryDao {
        return database.archiveDao()
    }

}