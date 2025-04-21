package com.virent.gweather.ui.components

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.virent.gweather.R
import com.virent.gweather.utils.extractUsername

@Composable
fun UserGreeting(email: String?) {
    email?.run {
        Text(
            text = stringResource(id = R.string.lbl_greetings_user, this.extractUsername()),
            color = Color.White.copy(alpha = 0.8f),
            style = typography.headlineSmall,
            fontWeight = FontWeight.Medium
        )
    } ?: @Composable {
        Text(
            text = stringResource(id = R.string.lbl_greetings),
            color = Color.White.copy(alpha = 0.8f),
            style = typography.headlineSmall,
            fontWeight = FontWeight.Medium
        )
    }
}