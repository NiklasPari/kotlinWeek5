package com.example.week5.ui

import com.example.week5.BuildConfig
import com.example.week5.data.model.WeatherResponse
import com.example.week5.data.remote.RetrofitInstance

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
data class WeatherUiState(
    val weatherData: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class WeatherViewModel : ViewModel() {
    var cityInput by mutableStateOf("")
        private set
    var uiState by mutableStateOf(WeatherUiState())
        private set
    fun updateCity(newCity: String) {
        cityInput = newCity
    }
    fun fetchWeather() {
        if (cityInput.isBlank()) return
        uiState = uiState.copy(isLoading = true, errorMessage = null)
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.getWeather(
                    city = cityInput,
                    apiKey = BuildConfig.API_KEY
                )
                uiState = uiState.copy(weatherData = result, isLoading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(
                    errorMessage = "Haku epäonnistui: ${e.localizedMessage}",
                    isLoading = false
                )
            }
        }
    }
}