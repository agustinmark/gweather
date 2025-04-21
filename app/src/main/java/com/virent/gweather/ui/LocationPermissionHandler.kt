package com.virent.gweather.ui

import android.Manifest
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.virent.gweather.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@RequiresPermission(
    allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION]
)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionHandler(
    onLocationFetched: (Location?) -> Unit,
    content: @Composable () -> Unit
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val coroutineScope = rememberCoroutineScope()
    val ctx = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(ctx) }

    if (locationPermissionState.allPermissionsGranted) {
        Log.e("Kiiro", "LocationPermissionHandler: allPermissionsGranted")
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                val priority = Priority.PRIORITY_HIGH_ACCURACY
                val result = locationClient.getCurrentLocation(
                    priority, CancellationTokenSource().token
                ).await()
                result?.let { onLocationFetched(it) } ?: onLocationFetched(null)
            }
        }
        content()
    } else { RequestPermission(locationPermissionState) }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun RequestPermission(locationPermissionState: MultiplePermissionsState) {
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