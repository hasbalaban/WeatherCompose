package com.weather.planetweather.models.forecast

import com.google.gson.annotations.SerializedName

data class Wind(
    val speed : Double,
    @SerializedName("deg")
    val deg : Double
)