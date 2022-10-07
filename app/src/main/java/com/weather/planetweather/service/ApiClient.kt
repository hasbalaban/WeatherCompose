package com.weather.planetweather.service

import com.weather.planetweather.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun retrofit(): ApiService = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Const.WEATHER_API_BASE_URL)
    .build()
    .create(ApiService::class.java)
}