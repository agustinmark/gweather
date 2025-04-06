package com.virent.gweather.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.data.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState: StateFlow<SignInUiState> =
        _uiState.stateIn(viewModelScope, SharingStarted.Lazily, SignInUiState.Idle)

    fun signIn(
        email: String,
        password: String,
        showSnackbar: (String) -> Unit,
        onSignedIn: () -> Unit
    ) {
        _uiState.value = SignInUiState.Loading
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                throwable.message?.let {
                    _uiState.value = SignInUiState.Idle
                    showSnackbar(it)
                }
            }
        ) {
            authRepository.signIn(email, password)
            _uiState.value = SignInUiState.Authenticated
            showSnackbar("Sign in successful")
            onSignedIn()
        }
    }
}

sealed class SignInUiState {
    data object Idle : SignInUiState()
    data object Loading : SignInUiState()
    data object Authenticated : SignInUiState()
}