package com.virent.gweather.ui.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.domain.GetCurrentWeatherUseCase
import com.virent.gweather.domain.InsertArchiveEntryUseCase
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
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val insertArchiveEntryUseCase: InsertArchiveEntryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherTodayUiState>(WeatherTodayUiState.Loading)
    var uiState: StateFlow<WeatherTodayUiState> = _uiState.asStateFlow()

    // call in LaunchedEffect Compose
    fun fetchWeather(lat: Double, lon: Double, units: String = "metric") {
        viewModelScope.launch {
            try {
                _uiState.value = WeatherTodayUiState.Loading
                val response = getCurrentWeatherUseCase(lat, lon, units)
                when (response) {
                    is Result.Success -> {
                        _uiState.value = WeatherTodayUiState.Success(data = response.data)
                        insertArchiveEntryUseCase("theyellowace@gmail.com", response.data)
                    }

                    is Result.Error -> _uiState.value =
                        WeatherTodayUiState.Error(message = response.message)
                }
            } catch (e: Exception) {
                _uiState.value = WeatherTodayUiState.Error(message = e.message)
            }
        }
    }
}

sealed class WeatherTodayUiState {
    data object Loading : WeatherTodayUiState()
    data class Error(val message: String?) : WeatherTodayUiState()
    data class Success(val data: WeatherData) : WeatherTodayUiState()
}