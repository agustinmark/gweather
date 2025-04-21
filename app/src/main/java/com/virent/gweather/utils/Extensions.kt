package com.virent.gweather.utils

import android.util.Patterns
import androidx.annotation.RawRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.virent.gweather.R
import com.virent.gweather.core.ui.icons.ClearSky
import com.virent.gweather.core.ui.icons.Clouds
import com.virent.gweather.core.ui.icons.GWeatherIcons
import com.virent.gweather.core.ui.icons.Mist
import com.virent.gweather.core.ui.icons.NightClearSky
import com.virent.gweather.core.ui.icons.NightClouds
import com.virent.gweather.core.ui.icons.NightRain
import com.virent.gweather.core.ui.icons.NightSnow
import com.virent.gweather.core.ui.icons.NightThunderstorm
import com.virent.gweather.core.ui.icons.Rain
import com.virent.gweather.core.ui.icons.ShowerRain
import com.virent.gweather.core.ui.icons.Snow
import com.virent.gweather.core.ui.icons.Thunderstorm
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

fun WeatherCondition.fetchImageVector(hour: Int): ImageVector {
    val isNight = hour in 18..23 || hour in 0..5

    return when (this) {
        WeatherCondition.THUNDERSTORM -> if (isNight) GWeatherIcons.NightThunderstorm else GWeatherIcons.Thunderstorm
        WeatherCondition.DRIZZLE -> GWeatherIcons.ShowerRain
        WeatherCondition.RAIN -> if (isNight) GWeatherIcons.NightRain else GWeatherIcons.Rain
        WeatherCondition.SNOW -> if (isNight) GWeatherIcons.NightSnow else GWeatherIcons.Snow
        WeatherCondition.ATMOSPHERE -> GWeatherIcons.Mist
        WeatherCondition.CLEAR -> if (isNight) GWeatherIcons.NightClearSky else GWeatherIcons.ClearSky
        WeatherCondition.CLOUDS -> if (isNight) GWeatherIcons.NightClouds else GWeatherIcons.Clouds
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