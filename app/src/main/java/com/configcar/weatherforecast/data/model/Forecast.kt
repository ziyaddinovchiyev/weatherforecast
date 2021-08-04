package com.configcar.weatherforecast.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val dt : Long,
    val temp : Temp,
    val weather: List<Weather>
) : Parcelable
