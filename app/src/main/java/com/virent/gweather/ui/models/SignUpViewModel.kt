package com.virent.gweather.ui.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.data.AuthenticationRepository
import com.virent.gweather.utils.isValidEmail
import com.virent.gweather.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun signUp(
        email: String,
        password: String,
        repeatPassword: String,
        showSnackbar: (String) -> Unit,
        onSignedIn: () -> Unit
    ) {
        if (!email.isValidEmail()) {
            showSnackbar("Invalid email.")
        }

        if (!password.isValidPassword()) {
            showSnackbar("Passwords should have at least eight digits and include one digit, one lower case letter and one upper case letter")
        }

        if (password != repeatPassword) {
            showSnackbar("Passwords do not match")
            return
        }

        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                throwable.message?.let {
                    showSnackbar(it)
                }
            }
        ) {
            _uiState.value = SignUpUiState.Loading
            authRepository.signUp(email, password)
            showSnackbar("Signing in..")
            authRepository.signIn(email, password)
            showSnackbar("Sign in successful")
            _uiState.value = SignUpUiState.Idle
            onSignedIn()
        }
    }
}

sealed class SignUpUiState {
    data object Idle : SignUpUiState()
    data object Loading : SignUpUiState()
}