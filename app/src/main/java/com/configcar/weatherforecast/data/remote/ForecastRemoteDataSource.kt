package com.configcar.weatherforecast.data.remote

import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    private val forecastService: ForecastService
) : BaseDataSource() {

    suspend fun getNextSevenDaysForecast() = getResult { forecastService.getNextSevenDaysForecast() }
}