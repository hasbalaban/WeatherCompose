package com.weather.planetweather.models.weather7days

import com.google.gson.annotations.SerializedName

data class Astro (

	val sunrise : String,
	val sunset : String,
	val moonrise : String,
	@SerializedName("moonset")
	val moonSet : String,
	@SerializedName("moon_phase")
	val moonPhase : String,
	@SerializedName("moon_illumination")
	val moonIllumination : Int
)