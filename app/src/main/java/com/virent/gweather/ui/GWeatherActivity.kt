package com.virent.gweather.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.virent.gweather.ui.theme.GWeatherTheme

class GWeatherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        enableEdgeToEdge()

        setContent {
            GWeatherTheme {
                GWeatherApp()
            }
        }
    }

}