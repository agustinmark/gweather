package com.virent.gweather.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.virent.gweather.core.ui.theme.GWeatherTheme

@Composable
fun ErrorIndicator(
    message: String? = "",
    onRetry: () -> Unit?
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_error))
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = ErrorIndicatorVerticalSpacing,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(ErrorIndicatorPadding)
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(ErrorIndicatorLottieSize)
        )
        Text(
            text = stringResource(R.string.txt_error),
            textAlign = TextAlign.Center,
            style = typography.titleLarge
        )
        message?.run {
            Text(text = this, textAlign = TextAlign.Center, style = typography.bodyLarge)
        }
        Button(onClick = { onRetry() }) { Text(text = stringResource(R.string.btn_retry)) }
    }
}

val ErrorIndicatorLottieSize = 300.dp
val ErrorIndicatorPadding = 24.dp
val ErrorIndicatorVerticalSpacing = 24.dp

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() { ErrorIndicator(message = "Error", onRetry = {}) }