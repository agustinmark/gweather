package com.virent.gweather.core.data.di

import com.virent.gweather.core.data.ArchiveRepository
import com.virent.gweather.core.data.OfflineArchiveRepository
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