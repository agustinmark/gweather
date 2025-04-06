package com.virent.gweather.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.virent.gweather.ui.theme.GWeatherTheme
import kotlinx.coroutines.launch

@Composable
fun GWeatherApp() {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    GWeatherTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize().imePadding(),
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            Box (
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            ) {
            NavHost(
                navController = navController,
                startDestination = LandingRoute,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<LandingRoute> {
                    LandingScreen(
                        openDashboard = {
                            navController.navigate(DashboardRoute) {
                                launchSingleTop = true
                            }
                        },
                        showSnackbar = { message ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(message)
                            }
                        }
                    )
                }
                composable<DashboardRoute> {
                    DashboardScreen(
                        onSignOut = {
                            navController.navigate(LandingRoute) { launchSingleTop = true }
                        },
                        showSnackbar = { message ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(message)
                            }
                        }
                    )
                }
            }}
        }
    }
}