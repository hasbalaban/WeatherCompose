package com.weather.planetweather.models.weather7days

import com.google.gson.annotations.SerializedName


data class Forecast (
	@SerializedName("forecastday")
	val forecastDay : List<ForecastDay>
)