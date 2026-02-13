package com.example.week5.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5.ui.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.cityInput,
            onValueChange = { viewModel.updateCity(it) },
            label = { Text("Syötä kaupungin nimi") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.fetchWeather() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hae sää")
        }

        Spacer(modifier = Modifier.height(32.dp))


        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.errorMessage?.let { error ->
            Text(text = error, color = Color.Red)
        }

        state.weatherData?.let { weather ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Kaupunki: ${weather.name}", style = MaterialTheme.typography.headlineSmall)
                    Text(text = "Lämpötila: ${weather.main.temp} °C", style = MaterialTheme.typography.bodyLarge)
                    val kuvaus = weather.weather.firstOrNull()?.description ?: "Ei kuvausta"
                    Text(text = "Sää: $kuvaus")
                    Text(text = "Ilmankosteus: ${weather.main.humidity}%")
                }
            }
        }
    }
}