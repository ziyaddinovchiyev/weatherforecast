package com.configcar.weatherforecast.data.repository

import com.configcar.weatherforecast.data.remote.ForecastRemoteDataSource
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource
) {
    suspend fun getNextSevenDaysForecast() = remoteDataSource.getNextSevenDaysForecast()
}