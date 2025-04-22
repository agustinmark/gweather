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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virent.gweather.R
import com.virent.gweather.core.ui.icons.Cloudiness
import com.virent.gweather.core.ui.icons.GWeatherIcons
import com.virent.gweather.core.ui.icons.Humidity
import com.virent.gweather.core.ui.icons.Wind
import com.virent.gweather.core.ui.theme.GWeatherTheme

@Composable
fun AdditionalInfo(
    cloudiness: Int,
    windSpeed: Double,
    windDegree: Int,
    humidity: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.surfaceContainer,
                shape = RoundedCornerShape(AdditionalInfoBackgroundCornerRadius)
            )
            .padding(all = AdditionalInfoPadding).alpha(0.8f)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(AdditionalInfoHorizontalSpacing),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = colorScheme.surfaceContainer)
        ) {
            AdditionalInfoItem(
                imageVector = GWeatherIcons.Cloudiness,
                value = stringResource(R.string.lbl_cloudiness_percent, cloudiness),
                label = stringResource(R.string.lbl_cloudiness),
                modifier = Modifier.weight(1f)
            )
            VerticalDivider(
                color = colorScheme.onSurface,
                modifier = Modifier.height(AdditionalInfoDividerHeight).alpha(0.6f)
            )
            AdditionalInfoItem(
                imageVector = GWeatherIcons.Wind,
                value = stringResource(R.string.lbl_wind_stats_metric, windDegree, windSpeed.toInt()),
                label = stringResource(R.string.lbl_wind),
                modifier = Modifier.weight(1f)
            )
            VerticalDivider(
                color = colorScheme.onSurface,
                modifier = Modifier.height(AdditionalInfoDividerHeight).alpha(0.6f)
            )
            AdditionalInfoItem(
                imageVector = GWeatherIcons.Humidity,
                value = stringResource(R.string.lbl_humidity_percent, humidity),
                label = stringResource(R.string.lbl_humidity),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun AdditionalInfoItem(
    imageVector: ImageVector,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(AdditionalInfoItemVerticalSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(AdditionalInfoItemIconSize),
            tint = colorScheme.onSurface
        )
        Text(text = value, style = typography.bodyLarge, color = colorScheme.onSurface)
        Text(text = label, style = typography.bodySmall, color = colorScheme.onSurface)
    }
}

val AdditionalInfoBackgroundCornerRadius = 12.dp
val AdditionalInfoPadding = 16.dp
val AdditionalInfoHorizontalSpacing = 12.dp
val AdditionalInfoDividerHeight = 32.dp

val AdditionalInfoItemIconSize = 48.dp
val AdditionalInfoItemVerticalSpacing = 4.dp

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() { AdditionalInfo(75, 4.12, 120, 62) }