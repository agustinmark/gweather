package com.virent.gweather.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archive")
data class ArchiveEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val user: String,
    val dateTime: Long,
    val offset: Int,
    val weather: String,
    val description: String,
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val cloudiness: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDegree: Int,
    val city: String,
    val countryCode: String,
    val sunrise: Long,
    val sunset: Long
)