package com.virent.gweather.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.virent.gweather.R
import com.virent.gweather.core.ui.theme.GWeatherTheme
import com.virent.gweather.viewmodels.SignInUiState
import com.virent.gweather.viewmodels.SignInViewModel

@Composable
fun SignIn(
    openDashboard: () -> Unit,
    showSnackbar: (String) -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isLoading = uiState is SignInUiState.Loading || uiState is SignInUiState.Authenticated

    fun signIn(email: String, password: String) {
        viewModel.signIn(
            email = email,
            password = password,
            showSnackbar = { message -> showSnackbar(message) },
            onSignedIn = openDashboard
        )
    }

    SignInDisplay(isLoading = isLoading, signIn = ::signIn)
}

@Composable
fun SignInDisplay(isLoading: Boolean, signIn: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().height(SignInLinearProgressIndicatorHeight)
            )
        }
        Spacer(modifier = Modifier.height(SignInDisplayTopSpacer))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = SignInDisplayHorizontalPadding),
            singleLine = true,
            value = email,
            enabled = !isLoading,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.lbl_email)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(SignInDisplayVerticalSpacer))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = SignInDisplayHorizontalPadding),
            singleLine = true,
            value = password,
            enabled = !isLoading,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.lbl_password)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(SignInPasswordMask)
        )
        Spacer(modifier = Modifier.height(SignInDisplayVerticalSpacer))
        Button(
            onClick = { signIn(email, password) },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().padding(horizontal = SignInDisplayHorizontalPadding)
        ) { Text(text = stringResource(R.string.btn_login), style = typography.titleMedium) }
        Spacer(modifier = Modifier.height(SignInDisplayBottomSpacer))
    }
}

val SignInDisplayTopSpacer = 24.dp
val SignInDisplayBottomSpacer = 36.dp
val SignInDisplayVerticalSpacer = 16.dp
val SignInDisplayHorizontalPadding = 32.dp

val SignInLinearProgressIndicatorHeight = 8.dp

const val SignInPasswordMask = '\u25CF'

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() { SignInDisplay(false) { _, _ -> } }