package com.virent.gweather.ui.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.domain.GetCurrentWeatherUseCase
import com.virent.gweather.domain.Result
import com.virent.gweather.domain.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherTodayViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherTodayUiState>(WeatherTodayUiState.Loading())
    var uiState: StateFlow<WeatherTodayUiState> = _uiState.asStateFlow()

    // call in LaunchedEffect Compose
    fun fetchWeather(lat: Double, lon: Double, units: String = "metric") {
        viewModelScope.launch {
            try {
                _uiState.value = WeatherTodayUiState.Loading()
                val response = getCurrentWeatherUseCase(lat, lon, units)
                when (response) {
                    is Result.Success -> {
                        _uiState.value = WeatherTodayUiState.Success(data = response.data)
                    }

                    is Result.Error -> _uiState.value =
                        WeatherTodayUiState.Error(errorMessage = response.message)
                }
            } catch (e: Exception) {
                _uiState.value = WeatherTodayUiState.Error(errorMessage = e.message)
            }
        }
    }
}

sealed class WeatherTodayUiState(open val isLoading: Boolean) {

    data class Loading(override val isLoading: Boolean = true) :
        WeatherTodayUiState(isLoading = isLoading)

    data class Error(val errorMessage: String?) : WeatherTodayUiState(isLoading = false)

    data class Success(val data: WeatherData) : WeatherTodayUiState(isLoading = false)
}