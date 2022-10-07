package com.weather.planetweather.models.forecast

data class MainWeather(
    val temp : Double,
    val feels_like : Double,
    val temp_min : Double,
    val temp_max : Double,
    val pressure : Double,
    val humidity : Double
)