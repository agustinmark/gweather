package com.virent.gweather.ui.tabs

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.virent.gweather.R
import com.virent.gweather.core.domain.model.WeatherCondition
import com.virent.gweather.core.domain.model.WeatherData
import com.virent.gweather.core.ui.ErrorIndicator
import com.virent.gweather.core.ui.LoadingIndicator
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.ui.components.AdditionalInfo
import com.virent.gweather.ui.components.SunInfo
import com.virent.gweather.ui.components.UserGreeting
import com.virent.gweather.ui.components.WeatherInfo
import com.virent.gweather.utils.LocationPermissionHandler
import com.virent.gweather.viewmodels.WeatherTodayUiState
import com.virent.gweather.viewmodels.WeatherTodayViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherTodayTab(
    onSignOut: () -> Unit,
    showSnackbar: (String) -> Unit,
    viewModel: WeatherTodayViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    var location by remember { mutableStateOf<Location?>(null) }

    val coroutineScope = rememberCoroutineScope()
    val ctx = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(ctx) }
    val locationErrorString = stringResource(R.string.error_location)

    fun fetchLocation(onLocationFetched: (Location?) -> Unit) {
        viewModel.showLoading()
        coroutineScope.launch {
            val priority = Priority.PRIORITY_HIGH_ACCURACY
            val result = locationClient.getCurrentLocation(
                priority, CancellationTokenSource().token
            ).await()
            result?.let { onLocationFetched(it) } ?: onLocationFetched(null)
        }
    }

    fun onLocationFetched(loc: Location?) {
        location = loc
        location?.run {
            viewModel.fetchWeather(latitude, longitude)
        } ?: viewModel.showError(locationErrorString)
    }

    fun retry() {
        location?.run {
            viewModel.fetchWeather(latitude, longitude)
        } ?: fetchLocation(::onLocationFetched)
    }

    LocationPermissionHandler(
        onLocationFetched = ::onLocationFetched
    ) { WeatherToday(uiState, { viewModel.signOut(onSignOut, showSnackbar) }, ::retry) }
}

@Composable
private fun WeatherToday(
    uiState: State<WeatherTodayUiState>,
    onSignOut: () -> Unit,
    onRetry: () -> Unit
) {
    when (uiState.value) {
        is WeatherTodayUiState.Loading -> LoadingIndicator()
        is WeatherTodayUiState.Success -> {
            val (email, weatherData) = (uiState.value as WeatherTodayUiState.Success)
            WeatherDisplay(
                email = email,
                onSignOut = onSignOut,
                weatherData = weatherData
            )
        }

        is WeatherTodayUiState.Error -> {
            val errorMessage = (uiState.value as WeatherTodayUiState.Error).message
            ErrorIndicator(
                message = errorMessage,
                onRetry = onRetry
            )
        }
    }
}

@Composable
private fun WeatherDisplay(
    email: String?,
    onSignOut: () -> Unit,
    weatherData: WeatherData
) {
    val coroutineScope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()
    val ctx = LocalContext.current

    val filePath = stringResource(R.string.bitmap_temp_path, weatherData.dateTime)

    fun createAndShareBitmap() {
        coroutineScope.launch {
            val bitmap = graphicsLayer.toImageBitmap().asAndroidBitmap()
            val file = File(ctx.cacheDir, filePath)
            file.outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }

            val uri = FileProvider.getUriForFile(ctx, "${ctx.packageName}.provider", file)
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/png"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            ctx.startActivity(Intent.createChooser(intent, "Share Screenshot"))
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(WeatherTodayVerticalSpacing, alignment = Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(WeatherTodayHeaderHorizontalSpacing),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = WeatherTodayContentPadding)
        ) {
            UserGreeting(email)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onSignOut) {
                Text(text = stringResource(R.string.btn_logout), style = typography.labelMedium)
            }
            FilledIconButton(onClick = ::createAndShareBitmap) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = null)
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = WeatherTodayVerticalSpacing,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
            .drawWithContent{
                graphicsLayer.record { this@drawWithContent.drawContent() }
                drawLayer(graphicsLayer)
            }.padding(WeatherTodayContentPadding)
        ) {
            WeatherInfo(
                dateTime = weatherData.dateTime,
                offset = weatherData.offset,
                weather = weatherData.weather,
                description = weatherData.description,
                temp = weatherData.temp,
                feelsLike = weatherData.feelsLike,
                tempMin = weatherData.tempMin,
                tempMax = weatherData.tempMax,
                city = weatherData.city,
                countryCode = weatherData.countryCode
            )
            SunInfo(
                offset = weatherData.offset,
                sunrise = weatherData.sunrise,
                sunset = weatherData.sunset
            )
            AdditionalInfo(
                cloudiness = weatherData.cloudiness,
                windSpeed = weatherData.windSpeed,
                windDegree = weatherData.windDegree,
                humidity = weatherData.humidity
            )
        }
    }
}

val WeatherTodayHeaderHorizontalSpacing = 8.dp
val WeatherTodayContentPadding = 24.dp
val WeatherTodayVerticalSpacing = 24.dp

@Preview(name = "Morning Preview")
@Composable
private fun MorningPreview() {
    GWeatherTheme {
        WeatherDisplay(
            email = "kiiro",
            onSignOut = {},
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
}

@Preview(name = "Evening Preview")
@Composable
private fun EveningPreview() {
    GWeatherTheme(forcedEveningMode = true) {
        WeatherDisplay(
            email = "kiiro",
            onSignOut = {},
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
}