package com.weather.planetweather.models.forecast


data class GeneralInfo(
    val type : String,
    val id : String,
    val country : String,
    val sunrise : String,
    val sunset : String,
)