package com.virent.gweather.ui.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.virent.gweather.core.domain.model.WeatherCondition
import com.virent.gweather.core.domain.model.WeatherData
import com.virent.gweather.core.network.model.Weather
import com.virent.gweather.core.ui.EmptyIndicator
import com.virent.gweather.core.ui.ErrorIndicator
import com.virent.gweather.core.ui.LoadingIndicator
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.ui.components.ArchiveEntry
import com.virent.gweather.viewmodels.WeatherArchiveUiState
import com.virent.gweather.viewmodels.WeatherArchiveViewModel

@Composable
fun WeatherArchiveTab(viewModel: WeatherArchiveViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    fun fetchUserArchive() { viewModel.retrieveCurrentUserArchive() }
    WeatherArchive(uiState, ::fetchUserArchive)
}

@Composable
private fun WeatherArchive(uiState: State<WeatherArchiveUiState>, fetchUserArchive: () -> Unit) {
    when (uiState.value) {
        is WeatherArchiveUiState.Loading -> LoadingIndicator()
        is WeatherArchiveUiState.Empty -> EmptyIndicator(onRefresh = fetchUserArchive)

        is WeatherArchiveUiState.Error -> {
            val errorMessage = (uiState.value as WeatherArchiveUiState.Error).message
            ErrorIndicator(message = errorMessage, onRetry = fetchUserArchive)
        }

        is WeatherArchiveUiState.Success -> {
            val archive = (uiState.value as WeatherArchiveUiState.Success).data
            ArchiveDisplay(archive)
        }
    }
}

@Composable
private fun ArchiveDisplay(archive: List<WeatherData>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(ArchiveDisplayVerticalSpacing),
        modifier = Modifier.fillMaxSize().padding(horizontal = ArchiveDisplayHorizontalPadding)
    ) {
        item { Spacer(modifier = Modifier.height(ArchiveDisplayVerticalSpacer)) }
        items(items = archive) { data -> ArchiveEntry(data) }
        item { Spacer(modifier = Modifier.height(ArchiveDisplayVerticalSpacer)) }
    }
}

val ArchiveDisplayVerticalSpacing = 12.dp
val ArchiveDisplayHorizontalPadding = 16.dp
val ArchiveDisplayVerticalSpacer = 12.dp


@Preview(name = "Morning Preview")
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview(name = "Evening Preview")
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() {
    val weatherData = WeatherData(
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
    val weatherData2 = WeatherData(
        dateTime = 1743828405,
        offset = 28800,
        weather = WeatherCondition.THUNDERSTORM,
        description = "stormy night",
        temp = 32.48,
        feelsLike = 38.55,
        tempMin = 31.62,
        tempMax = 32.92,
        cloudiness = 75,
        humidity = 61,
        windSpeed = 4.12,
        windDegree = 120,
        city = "Makati City",
        countryCode = "PH",
        sunrise = 1743803321,
        sunset = 1743847687
    )
    ArchiveDisplay(archive = listOf(weatherData, weatherData2))
}