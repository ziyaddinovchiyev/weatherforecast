package com.configcar.weatherforecast.data.remote

import com.configcar.weatherforecast.data.model.ForecastResponse
import com.configcar.weatherforecast.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ForecastService {

    @GET("onecall?lat=50.42289&lon=7.09757924&exclude=hourly,minutely,alerts&units=metric&appid=${Constants.API_KEY}")
    suspend fun getNextSevenDaysForecast() : Response<ForecastResponse>
}