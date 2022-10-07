package com.weather.planetweather.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.planetweather.models.weather7days.WeatherModel
import com.weather.planetweather.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val apiService : ApiService
    ):ViewModel() {

    val weather7Days = MutableLiveData<WeatherModel?>()

    fun weatherLoading(city :String?) : Flow<Status> = flow {
        emit(Status.LOADING)
        val defaultCity = "London"
        val searchCity =  if (city.isNullOrEmpty()) defaultCity else city
        val result = apiService.getForecastFromApi(cityName = searchCity)

        if (result.isSuccessful){
            weather7Days.value = result.body()
            emit(Status.SUCCESS)
            return@flow
        }
        emit(Status.FAIL)
    }


}

enum class Status{
    LOADING, SUCCESS, FAIL
}