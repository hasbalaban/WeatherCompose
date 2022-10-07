package com.weather.planetweather.models.weather7days

import com.google.gson.annotations.SerializedName

data class Hour (

	val time_epoch : Int,
	val time : String,
	@SerializedName("temp_c")
	val temp : Double,
	val temp_f : Double,
	val is_day : Int,
	val condition : Condition,
	val wind_mph : Double,
	val wind_kph : Double,
	val wind_degree : Int,
	val wind_dir : String,
	val pressure_mb : Int,
	val pressure_in : Double,
	val precip_mm : Double,
	val precip_in : Double,
	val humidity : Int,
	val cloud : Int,
	@SerializedName("feelslike_c")
	val feelsLike : Double,
	val feelslike_f : Double,
	val windchill_c : Double,
	val windchill_f : Double,
	val heatindex_c : Double,
	val heatindex_f : Double,
	val dewpoint_c : Double,
	val dewpoint_f : Double,
	val will_it_rain : Int,
	val chance_of_rain : Int,
	val will_it_snow : Int,
	val chance_of_snow : Int,
	val vis_km : Double,
	val vis_miles : Int,
	val gust_mph : Double,
	val gust_kph : Double,
	val uv : Int
)