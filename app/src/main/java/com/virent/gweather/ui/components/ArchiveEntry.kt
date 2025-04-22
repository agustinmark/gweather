package com.virent.gweather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virent.gweather.R
import com.virent.gweather.core.ui.icons.GWeatherIcons
import com.virent.gweather.core.ui.icons.Cloudiness
import com.virent.gweather.core.ui.icons.Humidity
import com.virent.gweather.core.ui.icons.Location
import com.virent.gweather.core.ui.icons.Sunrise
import com.virent.gweather.core.ui.icons.Sunset
import com.virent.gweather.core.ui.icons.TempMax
import com.virent.gweather.core.ui.icons.TempMin
import com.virent.gweather.core.ui.icons.Wind
import com.virent.gweather.core.domain.model.WeatherCondition
import com.virent.gweather.core.domain.model.WeatherData
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.utils.asDateTimeString
import com.virent.gweather.utils.asTimeString
import com.virent.gweather.utils.dateTimeHour
import com.virent.gweather.utils.fetchImageVector
import java.util.Locale

@Composable
fun ArchiveEntry(weatherData: WeatherData, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        weatherData.run {
            DateTimeInfo(dateTime = dateTime, offset = offset)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(ArchiveEntryHorizontalSpacing),
                modifier = Modifier.background(colorScheme.secondaryContainer)
                    .fillMaxWidth().padding(vertical = ArchiveEntryVerticalPadding, horizontal = ArchiveEntryHorizontalPadding)
            ) {
                WeatherLottie(weather = weather, dateTime = dateTime, offset = offset)
                ArchiveWeatherInfo(
                    tempMin = tempMin,
                    tempMax = tempMax,
                    temp = temp,
                    feelsLike = feelsLike
                )
                VerticalDivider(modifier = Modifier.height(ArchiveEntryDividerHeight))
                ArchiveAdditionalInfo(
                    scope = this@Row,
                    sunrise = sunrise,
                    sunset = sunset,
                    offset = offset,
                    cloudiness = cloudiness,
                    humidity = humidity,
                    windDegree = windDegree,
                    windSpeed = windSpeed
                )
            }
            LocationInfo(city = city, countryCode = countryCode)
        }
    }
}

@Composable
private fun DateTimeInfo(dateTime: Long, offset: Int) {
    Row(
        modifier = Modifier
            .background(colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(vertical = DateTimeInfoVerticalPadding, horizontal = DateTimeInfoHorizontalPadding)
    ) {
        Text(
            text = dateTime.asDateTimeString(offset),
            style = typography.labelMedium,
            color = colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun WeatherLottie(weather: WeatherCondition, dateTime: Long, offset: Int) {
    val imageVector = weather.fetchImageVector(dateTime.dateTimeHour(offset))
    Image(
        imageVector = imageVector,
        contentDescription = null,
        modifier = Modifier.size(WeatherLottieAnimationSize)
    )
}

@Composable
private fun ArchiveWeatherInfo(tempMin: Double, tempMax: Double, temp: Double, feelsLike: Double) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(ArchiveWeatherTempMixMaxHorizontalSpacing),
            modifier = Modifier.alpha(0.75f)
        ) {
            Icon(
                imageVector = GWeatherIcons.TempMin,
                contentDescription = null,
                modifier = Modifier.size(ArchiveWeatherTempMinMaxIconSize)
            )
            Text(
                text = stringResource(R.string.lbl_temperature, tempMin.toInt()),
                style = typography.labelSmall,
                color = colorScheme.onSecondaryContainer
            )
            VerticalDivider(modifier = Modifier.height(ArchiveWeatherTempMixMaxDividerHeight))
            Icon(
                imageVector = GWeatherIcons.TempMax,
                contentDescription = null,
                modifier = Modifier.size(ArchiveWeatherTempMinMaxIconSize)
            )
            Text(
                text = stringResource(R.string.lbl_temperature, tempMax.toInt()),
                style = typography.labelSmall,
                color = colorScheme.onSecondaryContainer
            )
        }
        Text(
            text = stringResource(
                R.string.lbl_temperature_celsius,
                temp.toInt()
            ),
            style = typography.displayMedium,
            color = colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(R.string.lbl_feels_like, feelsLike.toInt()),
            style = typography.labelMedium,
            color = colorScheme.onSecondaryContainer
        )
    }
}

@Composable
private fun ArchiveAdditionalInfo(
    scope: RowScope,
    sunrise: Long,
    sunset: Long,
    offset: Int,
    cloudiness: Int,
    humidity: Int,
    windDegree: Int,
    windSpeed: Double
) {
    scope.run {
        Column(
            verticalArrangement = Arrangement.spacedBy(AdditionalInfoVerticalSpacing),
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AdditionalInfoDetailHorizontalSpacing)
            ) {
                Icon(
                    imageVector = GWeatherIcons.Sunrise,
                    contentDescription = null,
                    modifier = Modifier.size(AdditionalInfoIconSize)
                )
                Text(
                    text = stringResource(R.string.lbl_sunrise),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
                Text(
                    text = sunrise.asTimeString(offset),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AdditionalInfoDetailHorizontalSpacing)
            ) {
                Icon(
                    imageVector = GWeatherIcons.Sunset,
                    contentDescription = null,
                    modifier = Modifier.size(AdditionalInfoIconSize)
                )
                Text(
                    text = stringResource(R.string.lbl_sunset),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
                Text(
                    text = sunset.asTimeString(offset),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AdditionalInfoDetailHorizontalSpacing)
            ) {
                Icon(
                    imageVector = GWeatherIcons.Cloudiness,
                    contentDescription = null,
                    modifier = Modifier.size(AdditionalInfoIconSize)
                )
                Text(
                    text = stringResource(R.string.lbl_cloudiness),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_cloudiness_percent, cloudiness),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AdditionalInfoDetailHorizontalSpacing)
            ) {
                Icon(
                    imageVector = GWeatherIcons.Wind,
                    contentDescription = null,
                    modifier = Modifier.size(AdditionalInfoIconSize)
                )
                Text(
                    text = stringResource(R.string.lbl_wind),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
                Text(
                    text = stringResource(
                        R.string.lbl_wind_stats_metric,
                        windDegree,
                        windSpeed.toInt()
                    ),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AdditionalInfoDetailHorizontalSpacing)
            ) {
                Icon(
                    imageVector = GWeatherIcons.Humidity,
                    contentDescription = null,
                    modifier = Modifier.size(AdditionalInfoIconSize)
                )
                Text(
                    text = stringResource(R.string.lbl_humidity),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_humidity_percent, humidity),
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Composable
private fun LocationInfo(city: String, countryCode: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(LocationInfoHorizontalSpacing),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(vertical = LocationInfoVerticalPadding, horizontal = LocationInfoHorizontalPadding)
    ) {
        Icon(
            imageVector = GWeatherIcons.Location,
            contentDescription = null,
            modifier = Modifier.size(LocationInfoIconSize)
        )
        Text(
            text = stringResource(
                R.string.lbl_location,
                city,
                Locale("", countryCode).displayName
            ),
            style = typography.labelMedium,
            color = colorScheme.onPrimaryContainer
        )
    }
}

val ArchiveEntryHorizontalSpacing = 8.dp
val ArchiveEntryVerticalPadding = 8.dp
val ArchiveEntryHorizontalPadding = 12.dp
val ArchiveEntryDividerHeight = 90.dp

val DateTimeInfoVerticalPadding = 8.dp
val DateTimeInfoHorizontalPadding = 12.dp

val WeatherLottieAnimationSize = 100.dp

val ArchiveWeatherTempMinMaxIconSize = 12.dp
val ArchiveWeatherTempMixMaxHorizontalSpacing = 4.dp
val ArchiveWeatherTempMixMaxDividerHeight = 12.dp

val AdditionalInfoVerticalSpacing = 4.dp
val AdditionalInfoDetailHorizontalSpacing = 8.dp
val AdditionalInfoIconSize = 16.dp

val LocationInfoVerticalPadding = 8.dp
val LocationInfoHorizontalPadding = 12.dp
val LocationInfoHorizontalSpacing = 4.dp
val LocationInfoIconSize = 16.dp

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() {
    ArchiveEntry(
        weatherData = WeatherData(
            dateTime = 1743827949,
            offset = 28800,
            weather = WeatherCondition.CLOUDS,
            description = "broken clouds",
            temp = 32.86,
            feelsLike = 39.86,
            tempMin = 31.14,
            tempMax = 33.0,
            cloudiness = 75,
            humidity = 62,
            windSpeed = 4.12,
            windDegree = 120,
            city = "Sambayanihan People's Village",
            countryCode = "PH",
            sunrise = 1743803336,
            sunset = 1743847698
        )
    )
}