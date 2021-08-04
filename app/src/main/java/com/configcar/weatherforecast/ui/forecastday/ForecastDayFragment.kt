package com.configcar.weatherforecast.ui.forecastday

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.request.RequestOptions
import com.configcar.weatherforecast.MainActivity
import com.configcar.weatherforecast.R
import com.configcar.weatherforecast.utils.GlideApp
import com.configcar.weatherforecast.utils.Utilities

class ForecastDayFragment : Fragment(R.layout.forecast_day_fragment) {

    private val args : ForecastDayFragmentArgs by navArgs()
    private lateinit var back: ImageView
    private lateinit var icon: ImageView
    private lateinit var day: TextView
    private lateinit var dayTemp: TextView
    private lateinit var minMaxTemp: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        showData()
    }

    @SuppressLint("SetTextI18n")
    private fun showData() {
        args.forecast.let { forecast ->
            val iconString = Utilities.iconUrl(icon = forecast.weather[0].icon)
            day.text = Utilities.dateToString(forecast.dt)
            dayTemp.text = forecast.temp.day.toInt().toString() + 0x00B0.toChar() +"C"
            minMaxTemp.text = forecast.temp.min.toInt().toString() + 0x00B0.toChar() +"C" + " / " + forecast.temp.max.toInt().toShort() + 0x00B0.toChar() +"C"
            GlideApp.with(requireContext())
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.weather).error(R.drawable.ic_baseline_error_outline_24))
                .load(iconString)
                .into(icon)
        }
    }

    private fun setupUI(view: View) {
        back = view.findViewById(R.id.back)
        icon = view.findViewById(R.id.icon)
        day = view.findViewById(R.id.day)
        dayTemp = view.findViewById(R.id.dayTemp)
        minMaxTemp = view.findViewById(R.id.minMaxTemp)

        back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}