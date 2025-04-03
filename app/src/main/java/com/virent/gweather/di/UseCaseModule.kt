package com.virent.gweather.di

import com.virent.gweather.domain.GetCurrentWeatherUseCase
import com.virent.gweather.domain.GetCurrentWeatherUseCaseImpl
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

}