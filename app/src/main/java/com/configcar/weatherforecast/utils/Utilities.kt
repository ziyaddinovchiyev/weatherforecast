package com.configcar.weatherforecast.utils

import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    companion object {

        fun iconUrl(icon: String) : String {
            return "https://openweathermap.org/img/wn/$icon@4x.png";
        }

        fun dateToString(dt: Long) : String{
            val sdf = SimpleDateFormat("EEEE, d MMMM")
            val date = Date(dt * 1000)
            return sdf.format(date)
        }
    }

}