package com.virent.gweather.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virent.gweather.R
import com.virent.gweather.ui.theme.GWeatherTheme


// TODO: To be used in Hourly Forecast
@Composable
fun WeatherHourly() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorScheme.secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 8.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .background(
                    color = colorScheme.secondaryContainer
                )
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            items(23) {
                WeatherHour()
            }
        }
    }
}

@Composable
fun WeatherHour() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = "05:51",
            style = typography.bodySmall,
            color = colorScheme.onSecondaryContainer
        )
        Text(
            stringResource(R.string.lbl_temperature_celsius, 33),
            style = typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = colorScheme.onSecondaryContainer
        )
        Image(
            painter = painterResource(id = R.drawable.ic_night_clouds),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_humidity),
                contentDescription = null,
                tint = colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .alpha(0.6f)
                    .size(16.dp)
            )
            Text(
                text = stringResource(R.string.lbl_humidity, 60),
                style = typography.bodySmall,
                color = colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview(
    showBackground = false,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name = "Dark Mode"
)
@Composable
fun WeatherHourlyPreview() {
    GWeatherTheme {
        WeatherHourly()
    }
}