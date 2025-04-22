package com.virent.gweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.virent.gweather.core.ui.theme.GWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GWeatherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { GWeatherTheme { GWeatherApp() } }
    }

}