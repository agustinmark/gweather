package com.virent.gweather.ui.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virent.gweather.domain.GetUserArchiveUseCase
import com.virent.gweather.domain.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherArchiveViewModel @Inject constructor(
    private val getUserArchiveUseCase: GetUserArchiveUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherArchiveUiState>(WeatherArchiveUiState.Empty)
    val uiState: StateFlow<WeatherArchiveUiState> = _uiState.asStateFlow()

    init {
        retrieveUserArchive("theyellowace@gmail.com")
    }

    fun retrieveUserArchive(user: String) {
        viewModelScope.launch {
            _uiState.value = WeatherArchiveUiState.Loading
            getUserArchiveUseCase(user)
                .catch { e ->
                    _uiState.value = WeatherArchiveUiState.Error(e.message ?: "Unknown error.")
                }
                .collect { archive ->
                    _uiState.value = when {
                        archive.isEmpty() -> WeatherArchiveUiState.Empty
                        else -> WeatherArchiveUiState.Success(archive)
                }
            }
        }
    }
}

sealed class WeatherArchiveUiState {
    data object Loading : WeatherArchiveUiState()
    data object Empty : WeatherArchiveUiState()
    data class Error(val message: String) : WeatherArchiveUiState()
    data class Success(val data: List<WeatherData>) : WeatherArchiveUiState()
}