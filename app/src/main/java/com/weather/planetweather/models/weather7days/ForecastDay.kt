package com.weather.planetweather.models.weather7days


data class ForecastDay (

	val date : String,
	val date_epoch : Int,
	val day : Day,
	val astro : Astro,
	val hour : List<Hour>
)