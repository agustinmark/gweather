package com.virent.gweather.di

import com.virent.gweather.domain.GetCurrentWeatherUseCase
import com.virent.gweather.domain.GetCurrentWeatherUseCaseImpl
import com.virent.gweather.domain.GetUserArchiveUseCase
import com.virent.gweather.domain.GetUserArchiveUseCaseImpl
import com.virent.gweather.domain.InsertArchiveEntryUseCase
import com.virent.gweather.domain.InsertArchiveEntryUseCaseImpl
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