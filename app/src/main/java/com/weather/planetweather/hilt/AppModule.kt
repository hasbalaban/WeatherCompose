package com.weather.planetweather.hilt

import com.weather.planetweather.service.ApiClient
import com.weather.planetweather.service.ApiService
import com.weather.planetweather.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton()
    @Provides
    fun retrofit () : ApiService{
        return ApiClient.retrofit()
    }
}