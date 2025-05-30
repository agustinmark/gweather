package com.virent.gweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.virent.gweather.core.ui.icons.Sunrise
import com.virent.gweather.core.ui.icons.Sunset
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.utils.asTimeString

@Composable
fun SunInfo(offset: Int, sunrise: Long, sunset: Long) {
    Card(
        modifier = Modifier.fillMaxWidth().background(
                color = colorScheme.surfaceContainer,
                shape = RoundedCornerShape(SunInfoBackgroundCornerRadius)
            ).padding(all = SunInfoPadding).alpha(0.8f)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SunInfoHorizontalSpacing),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = colorScheme.surfaceContainer)
        ) {
            Sunrise(offset, sunrise, Modifier.weight(1f))
            VerticalDivider(
                color = colorScheme.onSurface,
                modifier = Modifier.height(SunInfoDividerHeight).alpha(0.6f)
            )
            Sunset(offset, sunset, Modifier.weight(1f))
        }
    }
}

@Composable
fun Sunrise(offset: Int, sunrise: Long, modifier: Modifier = Modifier) {
    Icon(
        imageVector = GWeatherIcons.Sunrise,
        contentDescription = null,
        modifier = Modifier.size(SunInfoIconSize),
        tint = colorScheme.onSecondaryContainer
    )
    Column(horizontalAlignment = Alignment.Start, modifier = modifier) {
        Text(
            text = sunrise.asTimeString(offset),
            style = typography.headlineLarge,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onSecondaryContainer
        )
        Text(
            text = stringResource(R.string.lbl_sunrise),
            style = typography.bodySmall,
            color = colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun Sunset(offset: Int, sunset: Long, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.End, modifier = modifier) {
        Text(
            text = sunset.asTimeString(offset),
            style = typography.headlineLarge,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onSecondaryContainer
        )
        Text(
            text = stringResource(R.string.lbl_sunset),
            style = typography.bodySmall,
            color = colorScheme.onSecondaryContainer
        )
    }
    Icon(
        imageVector = GWeatherIcons.Sunset,
        contentDescription = null,
        modifier = Modifier.size(SunInfoIconSize),
        tint = colorScheme.onSecondaryContainer
    )
}

val SunInfoPadding = 16.dp
val SunInfoBackgroundCornerRadius = 12.dp
val SunInfoHorizontalSpacing = 12.dp
val SunInfoDividerHeight = 32.dp
val SunInfoIconSize = 48.dp

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() { SunInfo(offset = 28800, sunrise = 1743803336, sunset = 1743847698) }