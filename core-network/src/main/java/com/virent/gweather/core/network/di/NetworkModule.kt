package com.virent.gweather.core.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.virent.gweather.core.network.CurrentWeatherApi
import com.virent.gweather.core.network.OpenWeatherMap
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String {
        return OpenWeatherMap.BASE_URL
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherApi(retrofit: Retrofit): CurrentWeatherApi =
        retrofit.create(CurrentWeatherApi::class.java)
}