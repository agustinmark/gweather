package com.virent.gweather.utils

import android.util.Patterns
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.virent.gweather.R
import com.virent.gweather.domain.WeatherCondition
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.regex.Pattern

fun Long.asDateTimeString(offset: Int): String {
    val instant = Instant.ofEpochSecond(this)
    val localDateTime = instant.atZone(ZoneOffset.ofTotalSeconds(offset)).toLocalDateTime()
    return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("EEEE, dd-MMM-yyyy HH:mm"))
}

fun Long.asTimeString(offset: Int): String {
    val instant = Instant.ofEpochSecond(this)
    val localDateTime = instant.atZone(ZoneOffset.ofTotalSeconds(offset)).toLocalDateTime()
    return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"))
}

fun Long.dateTimeHour(offset: Int): Int {
    val instant = Instant.ofEpochSecond(this)
    val localDateTime = instant.atZone(ZoneOffset.ofTotalSeconds(offset)).toLocalDateTime()
    return localDateTime.hour
}

fun String.upperCaseFirst(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

@RawRes
fun WeatherCondition.fetchLottieResource(): Int {
    val currentHour = LocalTime.now().hour
    val isNight = currentHour in 18..23 || currentHour in 0..5

    return when (this) {
        WeatherCondition.THUNDERSTORM -> R.raw.lottie_thunderstorm
        WeatherCondition.DRIZZLE -> R.raw.lottie_shower_rain
        WeatherCondition.RAIN -> if (isNight) R.raw.lottie_night_rain else R.raw.lottie_rain
        WeatherCondition.SNOW -> R.raw.lottie_snow
        WeatherCondition.ATMOSPHERE -> R.raw.lottie_mist
        WeatherCondition.CLEAR -> if (isNight) R.raw.lottie_night_clear_sky else R.raw.lottie_clear_sky
        WeatherCondition.CLOUDS -> if (isNight) R.raw.lottie_night_clouds else R.raw.lottie_clouds
    }
}

@DrawableRes
fun WeatherCondition.fetchIconResource(hour: Int): Int {
    val isNight = hour in 18..23 || hour in 0..5

    return when (this) {
        WeatherCondition.THUNDERSTORM -> if (isNight) R.drawable.ic_night_thunderstorm else R.drawable.ic_thunderstorm
        WeatherCondition.DRIZZLE -> R.drawable.ic_shower_rain
        WeatherCondition.RAIN -> if (isNight) R.drawable.ic_night_rain else R.drawable.ic_rain
        WeatherCondition.SNOW -> if (isNight) R.drawable.ic_night_snow else R.drawable.ic_snow
        WeatherCondition.ATMOSPHERE -> R.drawable.ic_mist
        WeatherCondition.CLEAR -> if (isNight) R.drawable.ic_night_clear_sky else R.drawable.ic_clear_sky
        WeatherCondition.CLOUDS -> if (isNight) R.drawable.ic_night_clouds else R.drawable.ic_clouds
    }
}

private const val MIN_PASSWORD_LENGTH = 8
private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
            this.length >= MIN_PASSWORD_LENGTH &&
            Pattern.compile(PASSWORD_PATTERN).matcher(this).matches()
}

fun String.extractUsername(): String {
    val atIndex = this.indexOf('@')
    return if (atIndex == -1) ""
    else this.substring(0, atIndex)
}