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
import com.virent.gweather.viewmodels.SignUpUiState
import com.virent.gweather.viewmodels.SignUpViewModel

@Composable
fun SignUp(
    openDashboard: () -> Unit,
    showSnackbar: (String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isLoading = uiState is SignUpUiState.Loading

    fun signUp(email: String, password: String, repeatPassword: String) {
        viewModel.signUp(
            email = email,
            password = password,
            repeatPassword = repeatPassword,
            showSnackbar = { message -> showSnackbar(message) },
            onSignedIn = openDashboard
        )
    }

    SignUpDisplay(isLoading = isLoading, signUp = ::signUp)
}

@Composable
private fun SignUpDisplay(isLoading: Boolean, signUp: (String, String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().height(SignUpLinearProgressIndicatorHeight)
            )
        }
        Spacer(modifier = Modifier.height(SignUpDisplayTopSpacer))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = SignUpDisplayHorizontalPadding),
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
        Spacer(modifier = Modifier.height(SignUpDisplayVerticalSpacer))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = SignUpDisplayHorizontalPadding),
            singleLine = true,
            value = password,
            enabled = !isLoading,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.lbl_password)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(SignUpPasswordMask)
        )
        Spacer(modifier = Modifier.height(SignUpDisplayVerticalSpacer))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = SignUpDisplayHorizontalPadding),
            singleLine = true,
            value = repeatPassword,
            enabled = !isLoading,
            onValueChange = { repeatPassword = it },
            label = { Text(stringResource(R.string.lbl_repeat_password)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(SignUpPasswordMask)
        )
        Spacer(modifier = Modifier.height(SignUpDisplayVerticalSpacer))
        Button(
            onClick = { signUp(email, password, repeatPassword) },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().padding(horizontal = SignUpDisplayHorizontalPadding)
        ) { Text(text = stringResource(R.string.btn_register), style = typography.titleMedium) }
        Spacer(modifier = Modifier.height(SignUpDisplayBottomSpacer))
    }
}

val SignUpDisplayTopSpacer = 24.dp
val SignUpDisplayBottomSpacer = 36.dp
val SignUpDisplayVerticalSpacer = 16.dp
val SignUpDisplayHorizontalPadding = 32.dp

val SignUpLinearProgressIndicatorHeight = 8.dp

const val SignUpPasswordMask = '\u25CF'

@Preview
@Composable
private fun MorningPreview() { GWeatherTheme { PreviewContent() } }

@Preview
@Composable
private fun EveningPreview() { GWeatherTheme(forcedEveningMode = true) { PreviewContent() } }

@Composable
private fun PreviewContent() { SignUpDisplay(false) { _, _, _ -> } }