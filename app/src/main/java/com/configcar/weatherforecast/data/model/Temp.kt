package com.configcar.weatherforecast.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
) : Parcelable