package com.configcar.weatherforecast.utils

import com.configcar.weatherforecast.data.model.Forecast

interface RVClickListener {
    fun onItemClick(forecast: Forecast)
}