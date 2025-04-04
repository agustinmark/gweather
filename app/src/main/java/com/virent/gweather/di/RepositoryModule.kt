package com.virent.gweather.di

import com.virent.gweather.data.ArchiveRepository
import com.virent.gweather.data.OfflineArchiveRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindArchiveRepository(
        offlineArchiveRepository: OfflineArchiveRepository
    ): ArchiveRepository

}