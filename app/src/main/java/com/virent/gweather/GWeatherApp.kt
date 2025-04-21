package com.virent.gweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.virent.gweather.ui.DashboardRoute
import com.virent.gweather.ui.DashboardScreen
import com.virent.gweather.ui.LandingRoute
import com.virent.gweather.ui.LandingScreen
import kotlinx.coroutines.launch

@Composable
fun GWeatherApp() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    fun showSnackbarMessage(message: String) {
        coroutineScope.launch { snackbarHostState.showSnackbar(message) }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LandingRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<LandingRoute> {
                LandingScreen(
                    openDashboard = {
                        navController.navigate(DashboardRoute) { launchSingleTop = true }
                    },
                    showSnackbar = ::showSnackbarMessage
                )
            }
            composable<DashboardRoute> {
                DashboardScreen(
                    toLanding = {
                        navController.navigate(LandingRoute) { launchSingleTop = true }
                    },
                    showSnackbar = ::showSnackbarMessage
                )
            }
        }
    }
}