package com.weather.planetweather.view_models

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.io.InputStream
import java.lang.Exception

class SearchViewModel : ViewModel() {

    val locatedCities = MutableLiveData<List<String>>()
    var searchJob : Job?= Job()

    fun searchCityFromCityList(city : String, context : Context){
        searchJob?.cancel()
        searchJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                println("dd")
                val inputStream: InputStream = context.assets.open("cityNames.txt")
                val data = inputStream.bufferedReader().use { it.readText() }
                val textData = StringBuffer(data).toString()
                val cityList = textData.split(",")
                    .map { it.trim() }
                    .filter {
                    it.contains(city, ignoreCase = true)
                }
                withContext(Dispatchers.Main){
                    locatedCities.value = cityList
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}