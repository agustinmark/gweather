package com.virent.gweather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.virent.gweather.ui.screen.DashboardRoute
import com.virent.gweather.ui.screen.DashboardScreen
import com.virent.gweather.ui.screen.LandingRoute
import com.virent.gweather.ui.screen.LandingScreen
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