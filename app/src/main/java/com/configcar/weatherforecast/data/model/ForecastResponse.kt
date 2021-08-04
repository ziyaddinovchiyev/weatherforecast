package com.configcar.weatherforecast.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val daily : List<Forecast>
) : Parcelable
