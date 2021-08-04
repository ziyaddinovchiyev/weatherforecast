package com.configcar.weatherforecast.ui.forecastlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.configcar.weatherforecast.data.model.ForecastResponse
import com.configcar.weatherforecast.data.repository.ForecastRepository
import com.configcar.weatherforecast.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ForecastListViewModel @Inject constructor(private val repository: ForecastRepository) : ViewModel() {

    private val _forecastList = MutableLiveData<Resource<ForecastResponse>>()
    val forecastList: LiveData<Resource<ForecastResponse>>
        get() = _forecastList

    private var job: Job? = null

    init {
        fetchForecastList()
    }

    fun fetchForecastList() {
        _forecastList.postValue(Resource.loading(data = null))
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getNextSevenDaysForecast()
            withContext(Dispatchers.Main) {
                _forecastList.postValue(response)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}