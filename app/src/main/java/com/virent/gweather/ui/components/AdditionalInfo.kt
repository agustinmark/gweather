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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.virent.gweather.R

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
                color = colorScheme.tertiaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(all = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = colorScheme.tertiaryContainer)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cloudiness),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = colorScheme.onTertiaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_cloudiness_percent, cloudiness),
                    style = typography.bodyLarge,
                    color = colorScheme.onTertiaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_cloudiness),
                    style = typography.bodySmall,
                    color = colorScheme.onTertiaryContainer
                )
            }
            VerticalDivider(
                color = colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .height(32.dp)
                    .alpha(0.6f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_wind),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = colorScheme.onTertiaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_wind_stats_metric, windDegree, windSpeed.toInt()),
                    style = typography.bodyLarge,
                    color = colorScheme.onTertiaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_wind),
                    style = typography.bodySmall,
                    color = colorScheme.onTertiaryContainer
                )
            }
            VerticalDivider(
                color = colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .height(32.dp)
                    .alpha(0.6f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_humidity),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = colorScheme.onTertiaryContainer
                )
                Text(
                    text = stringResource(R.string.lbl_humidity_percent, humidity),
                    style = typography.bodyLarge,
                    color = colorScheme.onTertiaryContainer
                )
                Text(
                    text = "Humidity",
                    style = typography.bodySmall,
                    color = colorScheme.onTertiaryContainer
                )
            }
        }
    }
}
