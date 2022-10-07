package com.weather.planetweather.models.weather7days

data class WeatherModel (

	val location : Location,
	val current : Current,
	val forecast : Forecast,
	val alerts : Alerts
)