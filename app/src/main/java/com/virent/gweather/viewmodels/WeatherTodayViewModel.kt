package com.virent.gweather.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.data.AuthenticationRepository
import com.virent.gweather.domain.GetCurrentWeatherUseCase
import com.virent.gweather.domain.InsertArchiveEntryUseCase
import com.virent.gweather.domain.Result
import com.virent.gweather.domain.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherTodayViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val insertArchiveEntryUseCase: InsertArchiveEntryUseCase,
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherTodayUiState>(WeatherTodayUiState.Loading)
    var uiState: StateFlow<WeatherTodayUiState> = _uiState.asStateFlow()

    private val currentUser = authRepository.currentUser!!

    fun showError(message: String?) {
        _uiState.value = WeatherTodayUiState.Error(message = message)
    }

    fun showLoading() { _uiState.value = WeatherTodayUiState.Loading }

    fun fetchWeather(lat: Double, lon: Double, units: String = "metric") {
        Log.e("Kiiro", "vm.fetchWeather")
        viewModelScope.launch {
            try {
                _uiState.value = WeatherTodayUiState.Loading
                val response = getCurrentWeatherUseCase(lat, lon, units)
                when (response) {
                    is Result.Success -> {
                        _uiState.value = WeatherTodayUiState.Success(
                            email = currentUser.email,
                            data = response.data
                        )
                        insertArchiveEntryUseCase(
                            authRepository.currentUser!!.email!!,
                            response.data
                        )
                    }

                    is Result.Error -> showError(response.message)
                }
            } catch (e: Exception) { showError(e.message) }
        }
    }

    fun signOut(onSignOut: () -> Unit, showSnackbar: (String) -> Unit) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.message?.let {
                showSnackbar(it)
            }
        }
        ) {
            authRepository.signOut()
            onSignOut()
        }
    }
}

sealed class WeatherTodayUiState {
    data object Loading : WeatherTodayUiState()
    data class Error(val message: String?) : WeatherTodayUiState()
    data class Success(val email: String?, val data: WeatherData) : WeatherTodayUiState()
}