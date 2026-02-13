package com.example.week5.data.model

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String // Kaupungin nimi
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Weather(
    val description: String,
    val icon: String
)