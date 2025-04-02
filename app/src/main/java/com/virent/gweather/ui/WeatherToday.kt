package com.virent.gweather.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.virent.gweather.ui.theme.GWeatherTheme

@Composable
fun WeatherToday() {

}

@Preview(
    showBackground = false,
    name = "Light Mode")
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