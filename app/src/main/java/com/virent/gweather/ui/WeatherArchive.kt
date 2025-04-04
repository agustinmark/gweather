package com.virent.gweather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.virent.gweather.R
import com.virent.gweather.domain.WeatherData
import com.virent.gweather.ui.components.EmptyIndicator
import com.virent.gweather.ui.components.ErrorIndicator
import com.virent.gweather.ui.components.LoadingIndicator
import com.virent.gweather.ui.models.WeatherArchiveUiState
import com.virent.gweather.ui.models.WeatherArchiveViewModel
import com.virent.gweather.utils.asDateTimeString
import com.virent.gweather.utils.asTimeString
import com.virent.gweather.utils.dateTimeHour
import com.virent.gweather.utils.fetchIconResource
import java.util.Locale

@Composable
fun WeatherArchive(viewModel: WeatherArchiveViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    when (uiState.value) {
        is WeatherArchiveUiState.Loading -> LoadingIndicator()
        is WeatherArchiveUiState.Empty -> EmptyIndicator {
            viewModel.retrieveUserArchive("theyellowace@gmail.com")
        }

        is WeatherArchiveUiState.Error -> {
            val errorMessage = (uiState.value as WeatherArchiveUiState.Error).message
            ErrorIndicator(errorMessage) {
                viewModel.retrieveUserArchive("theyellowace@gmail.com")
            }
        }

        is WeatherArchiveUiState.Success -> {
            val archive = (uiState.value as WeatherArchiveUiState.Success).data
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                items(items = archive) { data ->
                    ArchiveEntry(data)
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun ArchiveEntry(weatherData: WeatherData, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        weatherData.run {
            Row(
                modifier = Modifier.background(colorScheme.tertiaryContainer)
                    .fillMaxWidth().padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                Text(
                    text = dateTime.asDateTimeString(offset),
                    style = typography.labelMedium,
                    color = colorScheme.onTertiaryContainer
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.background(colorScheme.secondaryContainer)
                    .fillMaxWidth().padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                val imageRes = weather.fetchIconResource(
                    dateTime.dateTimeHour(offset)
                )
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.alpha(0.75f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_temp_min),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = stringResource(
                                R.string.lbl_temperature,
                                tempMin.toInt()
                            ),
                            style = typography.labelSmall,
                            color = colorScheme.onSecondaryContainer
                        )
                        VerticalDivider(modifier = Modifier.height(12.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_temp_max),
                            contentDescription = null,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = stringResource(
                                R.string.lbl_temperature,
                                tempMax.toInt()
                            ),
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
                VerticalDivider(modifier = Modifier.height(90.dp))
                Column (
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sunrise),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sunset),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cloudiness),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_wind),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = stringResource(R.string.lbl_wind),
                            style = typography.labelMedium,
                            color = colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = stringResource(R.string.lbl_wind_stats_metric, windDegree, windSpeed.toInt()),
                            style = typography.labelMedium,
                            color = colorScheme.onSecondaryContainer
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_humidity),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(colorScheme.tertiaryContainer)
                    .fillMaxWidth().padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = stringResource(
                        R.string.lbl_location,
                        city,
                        Locale("", countryCode).displayName
                    ),
                    style = typography.labelMedium,
                    color = colorScheme.onTertiaryContainer
                )
            }
        }
    }
}