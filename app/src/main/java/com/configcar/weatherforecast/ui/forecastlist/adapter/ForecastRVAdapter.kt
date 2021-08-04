package com.configcar.weatherforecast.ui.forecastlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.configcar.weatherforecast.R
import com.configcar.weatherforecast.data.model.Forecast
import com.configcar.weatherforecast.ui.forecastlist.viewholder.ForecastTodayViewHolder
import com.configcar.weatherforecast.ui.forecastlist.viewholder.ForecastViewHolder
import com.configcar.weatherforecast.utils.DiffCallback
import com.configcar.weatherforecast.utils.RVClickListener

class ForecastRVAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = emptyList<Forecast>()
    private var rvClickListener: RVClickListener? = null

    fun updateData(update : List<Forecast>) {
        val diffCallback = DiffCallback(items, update)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = update
        diffResult.dispatchUpdatesTo(this)
    }

    fun setItemClickListener(RVClickListener: RVClickListener) {
        this.rvClickListener = RVClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View
        if (viewType == Type.TODAY.ordinal) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_today, parent, false)
            return ForecastTodayViewHolder(view)
        }
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return Type.TODAY.ordinal
        return Type.REST.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Type.TODAY.ordinal) {
            (holder as ForecastTodayViewHolder).bind(forecast = items[position])
        } else if (holder.itemViewType == Type.REST.ordinal) {
            (holder as ForecastViewHolder).bind(forecast = items[position])
        }
        holder.itemView.setOnClickListener {
            rvClickListener?.onItemClick(items[position])
        }
    }

    override fun getItemCount() = items.size

    enum class Type {
        TODAY, REST
    }
}