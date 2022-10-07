package com.weather.planetweather.models.weather7days

import com.google.gson.annotations.SerializedName

data class Day (

	@SerializedName("maxtemp_c")
	val maxTempC : Double,
	val maxtemp_f : Double,
	@SerializedName("mintemp_c")
	val minTempC : Double,
	val mintemp_f : Double,
	@SerializedName("avgtemp_c")
	val avgTempC : Double,
	val avgtemp_f : Double,
	@SerializedName("maxwind_mph")
	val maxWindMph : Double,
	@SerializedName("maxwind_kph")
	val maxWindKph : Double,
	val totalprecip_mm : Double,
	val totalprecip_in : Double,
	@SerializedName("avgvis_km")
	val avgVisKm : Double,
	val avgvis_miles : Double,
	@SerializedName("avghumidity")
	val avgHumidity : Double,
	@SerializedName("daily_will_it_rain")
	val dailyWillItRain : Int,
	@SerializedName("daily_chance_of_rain")
	val dailyChanceOfRain : Int,
	@SerializedName("daily_will_it_snow")
	val dailyWillItSnow : Int,
	@SerializedName("daily_chance_of_snow")
	val dailyChanceOfSnow : Int,
	val condition : Condition,
	val uv : Int
)