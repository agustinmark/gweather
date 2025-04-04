package com.virent.gweather.ui.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.data.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun signIn(
        email: String,
        password: String,
        showSnackbar: (String) -> Unit,
        onSignedIn: () -> Unit
    ) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                throwable.message?.let {
                    showSnackbar(it)
                }
            }
        ) {
            _uiState.value = SignInUiState.Loading
            authRepository.signIn(email, password)
            _uiState.value = SignInUiState.Idle
            showSnackbar("Sign in successful")
            onSignedIn()
        }
    }
}

sealed class SignInUiState {
    data object Idle : SignInUiState()
    data object Loading : SignInUiState()
}