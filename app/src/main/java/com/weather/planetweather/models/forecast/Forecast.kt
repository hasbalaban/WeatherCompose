package com.weather.planetweather.models.forecast

import com.google.gson.annotations.SerializedName

data class Forecast(
    val id: String,
    @SerializedName("coord")
    val coordinate : Coordinate,
    val weather : ArrayList<Weather>,
    val weather1 : ArrayList<Weather>,
    val base : String,
    @SerializedName("main")
    val mainWeather : MainWeather,
    val visibility : Double,
    val wind: Wind,
    val clouds: Clouds,
    val dt: String,
    @SerializedName("sys")
    val generalInfo: GeneralInfo,
    val timezone: Double,
    @SerializedName("name")
    val cityName: String,
    val code: Double
)










