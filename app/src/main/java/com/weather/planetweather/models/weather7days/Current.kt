package com.weather.planetweather.models.weather7days

import com.google.gson.annotations.SerializedName

data class Current (

	@SerializedName("last_updated_epoch")
	val last_updated_epoch : Int,
	@SerializedName("last_updated")
	val lastUpdated : String,
	@SerializedName("temp_c")
	val tempC : Double,
	@SerializedName("temp_f")
	val tempF : Double,
	@SerializedName("is_day")
	val isDay : Int,
	val condition : Condition,
	@SerializedName("wind_mph")
	val windMph : Double,
	@SerializedName("wind_kph")
	val windKph : Double,
	@SerializedName("wind_degree")
	val windDegree : Int,
	@SerializedName("wind_dir")
	val windDir : String,
	@SerializedName("pressure_mb")
	val pressureMb : Int,
	@SerializedName("pressure_in")
	val pressureIn : Double,
	@SerializedName("precip_mm")
	val precipMm : Double,
	@SerializedName("precip_in")
	val precipIn : Double,
	@SerializedName("humidity")
	val humidity : Int,
	@SerializedName("cloud")
	val cloud : Int,
	@SerializedName("feelslike_c")
	val feelslikeC : Double,
	@SerializedName("feelslike_f")
	val feelslikeF : Double,
	@SerializedName("vis_km")
	val visKm : Double,
	@SerializedName("vis_miles")
	val visMiles : Double,
	val uv : Int,
	@SerializedName("gust_mph")
	val gustMph : Double,
	@SerializedName("gust_kph")
	val gustKph : Double,
	@SerializedName("air_quality")
	val airQuality : AirQuality
)