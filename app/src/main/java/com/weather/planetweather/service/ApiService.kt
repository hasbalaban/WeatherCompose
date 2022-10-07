package com.weather.planetweather.service

import com.weather.planetweather.models.forecast.Forecast
import com.weather.planetweather.models.weather7days.WeatherModel
import com.weather.planetweather.utils.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("daily")
    suspend fun latLongDailyForecast (
        @Query("lat") latitude : String,
        @Query("lon") longitude : String,
        @Query("cnt") dayNumbers : Int,
        @Query("appid") apiKey : String = Const.API_KEY,
        ) : Response<Any>

    @GET("/data/2.5/weather")
    suspend fun cityWeatherForecast (
        @Query("q") cityName : String,
        @Query("appid") apiKey : String = Const.API_KEY,
        @Query("units") units : String = "metric",
        ) : Response<Forecast>


    @GET("v1/forecast.json")
    suspend fun getForecastFromApi(
        @Query("q") cityName: String,
        @Query("days") days: Int = 3,
        @Query("key") key: String = Const.API_KEY,
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "yes")
    : Response<WeatherModel>
}