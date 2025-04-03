package com.virent.gweather.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.virent.gweather.R
import com.virent.gweather.domain.WeatherCondition
import com.virent.gweather.ui.components.AdditionalInfo
import com.virent.gweather.ui.components.ErrorIndicator
import com.virent.gweather.ui.components.LoadingIndicator
import com.virent.gweather.ui.components.SunInfo
import com.virent.gweather.ui.models.WeatherTodayUiState
import com.virent.gweather.ui.models.WeatherTodayViewModel
import com.virent.gweather.ui.theme.GWeatherTheme
import com.virent.gweather.utils.asDateTimeString
import com.virent.gweather.utils.fetchLottieResource
import com.virent.gweather.utils.sentenceCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherToday(viewModel: WeatherTodayViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val uiState = viewModel.uiState.collectAsState()
    var location by remember { mutableStateOf<Location?>(null) }

    LocationPermissionHandler(
        onLocationFetched = { loc ->
            location = loc
            // TODO: Add Measurement System to Preferences
            location?.run {
                viewModel.fetchWeather(latitude, longitude)
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = scrollState
                )
                .padding(24.dp)
                .fillMaxSize()
        ) {
            when (uiState.value) {
                is WeatherTodayUiState.Loading -> LoadingIndicator()
                is WeatherTodayUiState.Success -> {
                    val weatherData = (uiState.value as WeatherTodayUiState.Success).data
                    UserGreeting()
                    Spacer(modifier = Modifier.height(24.dp))
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
                    Spacer(modifier = Modifier.height(24.dp))
                    SunInfo(
                        offset = weatherData.offset,
                        sunrise = weatherData.sunrise,
                        sunset = weatherData.sunset
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    AdditionalInfo(
                        cloudiness = weatherData.cloudiness,
                        windSpeed = weatherData.windSpeed,
                        windDegree = weatherData.windDegree,
                        humidity = weatherData.humidity
                    )
                }

                is WeatherTodayUiState.Error -> {
                    val errorMessage = (uiState.value as WeatherTodayUiState.Error).errorMessage
                    ErrorIndicator(
                        message = errorMessage,
                        onRetry = {
                            location?.run {
                                Log.v("TEST", "location: $latitude,$longitude")
                                viewModel.fetchWeather(latitude, longitude)
                            }
                        }
                    )
                }
            }
        }
    }
}

@RequiresPermission(
    allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionHandler(
    onLocationFetched: (Location?) -> Unit,
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(ctx)
    }
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    if (locationPermissionState.allPermissionsGranted) {
        LaunchedEffect(Unit) {
            scope.launch {
                val priority = Priority.PRIORITY_HIGH_ACCURACY
                val result = locationClient.getCurrentLocation(
                    priority, CancellationTokenSource().token
                ).await()
                result?.let { fetchedLocation ->
                    onLocationFetched(fetchedLocation)
                }
            }
        }
        content()
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            val allPermissionsRevoked =
                locationPermissionState.permissions.size ==
                        locationPermissionState.revokedPermissions.size

            val messageRes = if (!allPermissionsRevoked) {
                R.string.txt_location_permission_partial
            } else if (locationPermissionState.shouldShowRationale) {
                R.string.txt_location_permission_permissions_denied
            } else {
                R.string.txt_location_permission_request
            }

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_locating))
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(messageRes),
                textAlign = TextAlign.Center,
                style = typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { locationPermissionState.launchMultiplePermissionRequest() }
            ) {
                Text(
                    text = stringResource(R.string.btn_grant_location_permission)
                )
            }
        }
    }
}

@Composable
fun UserGreeting() {
    // TODO: Update after sign-in implementation
    Text(
        text = stringResource(id = R.string.lbl_greetings_user, "Mark"),
        style = typography.headlineSmall,
        fontWeight = FontWeight.Medium
    )
}

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
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.alpha(0.75f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_temp_min),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(text = stringResource(R.string.lbl_temperature, tempMin.toInt()))
                    VerticalDivider(modifier = Modifier.height(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_temp_max),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(text = stringResource(R.string.lbl_temperature, tempMax.toInt()))
                }
                Text(
                    text = stringResource(R.string.lbl_temperature_celsius, temp.toInt()),
                    style = typography.displayLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = stringResource(R.string.lbl_feels_like, feelsLike.toInt()),
                    style = typography.titleMedium
                )
                Text(
                    text = description.sentenceCase(),
                    style = typography.titleMedium
                )
            }
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(180.dp)
            )
        }
        Text(
            text = stringResource(R.string.lbl_location, city, Locale("", countryCode).displayName),
            style = typography.titleMedium,
            modifier = Modifier.alpha(.8f)
        )
        Text(
            text = dateTime.asDateTimeString(offset),
            style = typography.titleMedium,
            modifier = Modifier.alpha(.8f)
        )
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
fun WeatherTodayPreview() {
    GWeatherTheme {
        WeatherToday()
    }
}