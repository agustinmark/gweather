package com.virent.gweather.di

import android.content.Context
import androidx.room.Room
import com.virent.gweather.data.ArchiveDatabase
import com.virent.gweather.data.ArchiveEntryDao
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
            ArchiveDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideArchiveEntryDao(
        database: ArchiveDatabase
    ): ArchiveEntryDao {
        return database.archiveDao()
    }

}