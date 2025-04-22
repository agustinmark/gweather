package com.virent.gweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.virent.gweather.R
import com.virent.gweather.core.ui.icons.GWeatherIcons
import com.virent.gweather.core.ui.icons.TempMax
import com.virent.gweather.core.ui.icons.TempMin
import com.virent.gweather.core.domain.model.WeatherCondition
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.utils.asDateTimeString
import com.virent.gweather.utils.fetchLottieResource
import com.virent.gweather.utils.upperCaseFirst
import java.util.Locale


@Composable
fun WeatherInfo(
    dateTime: Long,
    offset: Int,
    weather: WeatherCondition,
    description: String,
    temp: Double,
    feelsLike: Double,
    tempMin: Double,
    tempMax: Double,
    city: String,
    countryCode: String
) {
    val lottieRes = weather.fetchLottieResource()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))
    Column(
        verticalArrangement = Arrangement.spacedBy(WeatherInfoVerticalSpacing)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(TemperatureInfoVerticalSpacing)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(TemperatureMinMaxHorizontalSpacing),
                    modifier = Modifier.alpha(0.8f)
                ) {
                    Icon(
                        imageVector = GWeatherIcons.TempMin,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.size(TemperatureMinMaxIconSize)
                    )
                    Text(
                        text = stringResource(R.string.lbl_temperature, tempMin.toInt()),
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    VerticalDivider(modifier = Modifier.height(12.dp))
                    Icon(
                        imageVector = GWeatherIcons.TempMax,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.size(TemperatureMinMaxIconSize)
                    )
                    Text(
                        text = stringResource(R.string.lbl_temperature, tempMax.toInt()),
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
                Text(
                    text = stringResource(R.string.lbl_temperature_celsius, temp.toInt()),
                    style = typography.displayLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Text(
                    text = stringResource(R.string.lbl_feels_like, feelsLike.toInt()),
                    style = typography.titleMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Text(
                    text = description.upperCaseFirst(),
                    style = typography.titleMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(WeatherInfoLottieAnimationSize)
            )
        }
        Text(
            text = stringResource(R.string.lbl_location, city, Locale("", countryCode).displayName),
            style = typography.titleMedium,
            color = Color.White.copy(alpha = 0.9f)
        )
        Text(
            text = dateTime.asDateTimeString(offset),
            style = typography.titleMedium,
            color = Color.White.copy(alpha = 0.9f)
        )
    }
}

val WeatherInfoVerticalSpacing = 8.dp
val WeatherInfoLottieAnimationSize = 180.dp

val TemperatureInfoVerticalSpacing = 4.dp
val TemperatureMinMaxHorizontalSpacing = 4.dp
val TemperatureMinMaxIconSize = 16.dp

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() { 
    WeatherInfo(
        dateTime = 1743827949,
        offset = 28800,
        weather = WeatherCondition.CLOUDS,
        description = "broken clouds",
        temp = 32.86,
        feelsLike = 39.86,
        tempMin = 31.14,
        tempMax = 33.0,
        city = "Sambayanihan People's Village",
        countryCode = "PH"
    )
}