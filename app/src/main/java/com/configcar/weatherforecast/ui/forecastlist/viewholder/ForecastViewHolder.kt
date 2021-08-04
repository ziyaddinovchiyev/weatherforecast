package com.configcar.weatherforecast.ui.forecastlist.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.configcar.weatherforecast.R
import com.configcar.weatherforecast.data.model.Forecast
import com.configcar.weatherforecast.utils.Utilities

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val day = view.findViewById<TextView>(R.id.day)
    private val dayTemp = view.findViewById<TextView>(R.id.dayTemp)
    private val minMaxTemp = view.findViewById<TextView>(R.id.minMaxTemp)

    @SuppressLint("SetTextI18n")
    fun bind(forecast: Forecast) {
        day.text = Utilities.dateToString(forecast.dt)
        dayTemp.text = "Day: " + forecast.temp.day.toInt().toString() + 0x00B0.toChar() +"C"
        minMaxTemp.text = forecast.temp.min.toInt().toString() + 0x00B0.toChar() +"C" + " / " + forecast.temp.max.toInt().toShort() + 0x00B0.toChar() +"C"
    }
}