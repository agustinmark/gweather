package com.virent.gweather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.virent.gweather.R
import com.virent.gweather.core.ui.icons.Cloudiness
import com.virent.gweather.core.ui.icons.GWeatherIcons
import com.virent.gweather.core.ui.icons.Humidity
import com.virent.gweather.core.ui.icons.Location
import com.virent.gweather.core.ui.icons.Sunrise
import com.virent.gweather.core.ui.icons.Sunset
import com.virent.gweather.core.ui.icons.TempMax
import com.virent.gweather.core.ui.icons.TempMin
import com.virent.gweather.core.ui.icons.Wind
import com.virent.gweather.domain.WeatherData
import com.virent.gweather.core.ui.EmptyIndicator
import com.virent.gweather.core.ui.ErrorIndicator
import com.virent.gweather.core.ui.LoadingIndicator
import com.virent.gweather.ui.components.ArchiveEntry
import com.virent.gweather.viewmodels.WeatherArchiveUiState
import com.virent.gweather.viewmodels.WeatherArchiveViewModel
import com.virent.gweather.utils.asDateTimeString
import com.virent.gweather.utils.asTimeString
import com.virent.gweather.utils.dateTimeHour
import com.virent.gweather.utils.fetchImageVector
import java.util.Locale

@Composable
fun WeatherArchiveTab(viewModel: WeatherArchiveViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    fun fetchUserArchive() {
        viewModel.retrieveCurrentUserArchive()
    }

    WeatherArchive(uiState, ::fetchUserArchive)
}

@Composable
private fun WeatherArchive(
    uiState: State<WeatherArchiveUiState>,
    fetchUserArchive: () -> Unit
) {
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