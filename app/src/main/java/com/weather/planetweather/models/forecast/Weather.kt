package com.weather.planetweather.models.forecast


data class Weather(
    val id : String,
    val main : String,
    val description : String,
    val icon : String
)