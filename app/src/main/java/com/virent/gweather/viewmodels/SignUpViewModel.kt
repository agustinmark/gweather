package com.virent.gweather.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.core.data.AuthenticationRepository
import com.virent.gweather.utils.isValidEmail
import com.virent.gweather.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
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
        _uiState.value = SignUpUiState.Loading
        if (!email.isValidEmail()) {
            _uiState.value = SignUpUiState.Idle
            showSnackbar("Invalid email.")
            return
        }

        if (!password.isValidPassword()) {
            _uiState.value = SignUpUiState.Idle
            showSnackbar("Passwords should have at least eight digits and include one digit, one lower case letter and one upper case letter")
            return
        }

        if (password != repeatPassword) {
            _uiState.value = SignUpUiState.Idle
            showSnackbar("Passwords do not match")
            return
        }

        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                throwable.message?.let {
                    _uiState.value = SignUpUiState.Idle
                    showSnackbar(it)
                }
            }
        ) {
            authRepository.signUp(email, password)
            showSnackbar("Signing in..")
            _uiState.value = SignUpUiState.Success
            authRepository.signIn(email, password)
            showSnackbar("Sign in successful")
            _uiState.value = SignUpUiState.Authenticated
            onSignedIn()
        }
    }
}

sealed class SignUpUiState {
    data object Idle : SignUpUiState()
    data object Loading : SignUpUiState()
    data object Success : SignUpUiState()
    data object Authenticated : SignUpUiState()
}