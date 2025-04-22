package com.virent.gweather.core.domain.di

import com.virent.gweather.core.domain.GetCurrentWeatherUseCase
import com.virent.gweather.core.domain.GetCurrentWeatherUseCaseImpl
import com.virent.gweather.core.domain.GetUserArchiveUseCase
import com.virent.gweather.core.domain.GetUserArchiveUseCaseImpl
import com.virent.gweather.core.domain.InsertArchiveEntryUseCase
import com.virent.gweather.core.domain.InsertArchiveEntryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetCurrentWeatherUseCase(
        currentWeatherUseCaseImpl: GetCurrentWeatherUseCaseImpl
    ): GetCurrentWeatherUseCase

    @Binds
    abstract fun bindGetUserArchiveUseCase(
        getUserArchiveUseCaseImpl: GetUserArchiveUseCaseImpl
    ): GetUserArchiveUseCase

    @Binds
    abstract fun bindInsertArchiveEntryUseCase(
        insertArchiveEntryUseCase: InsertArchiveEntryUseCaseImpl
    ) : InsertArchiveEntryUseCase
}