package com.configcar.weatherforecast.ui.forecastlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.configcar.weatherforecast.R
import com.configcar.weatherforecast.data.model.Forecast
import com.configcar.weatherforecast.ui.forecastlist.adapter.ForecastRVAdapter
import com.configcar.weatherforecast.ui.forecastlist.viewmodel.ForecastListViewModel
import com.configcar.weatherforecast.utils.RVClickListener
import com.configcar.weatherforecast.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastListFragment : Fragment(R.layout.forecast_list_fragment),
    RVClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: ForecastListViewModel by viewModels()
    private val forecastAdapter = ForecastRVAdapter()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorLabel: TextView
    private lateinit var loadingIndicator: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        setupObservers()
    }

    // observing forecast list from viewModel
    private fun setupObservers() {
        viewModel.forecastList.observe(viewLifecycleOwner, { forecast ->
            when (forecast.status) {
                Resource.Status.LOADING -> {
                    recyclerView.visibility = View.GONE
                    loadingIndicator.visibility = View.VISIBLE
                    errorLabel.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    forecastAdapter.updateData(forecast.data!!.daily)
                    recyclerView.visibility = View.VISIBLE
                    loadingIndicator.visibility = View.GONE
                    errorLabel.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    recyclerView.visibility = View.GONE
                    loadingIndicator.visibility = View.GONE
                    errorLabel.visibility = View.VISIBLE
                }
            }
            if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
        })
    }

    // defining ui elements
    private fun setupUI(view: View) {
        loadingIndicator = view.findViewById(R.id.loadingIndicator)
        errorLabel = view.findViewById(R.id.errorLabel)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener(this)
        forecastAdapter.setItemClickListener(this)
        recyclerView = view.findViewById(R.id.forecastList)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = forecastAdapter
        }
    }

    // recyclerview item click callback
    override fun onItemClick(forecast: Forecast) {
        findNavController().navigate(ForecastListFragmentDirections.actionForecastListFragmentToForecastDayFragment(forecast))
    }

    // Swipe refresh layout callback
    override fun onRefresh() {
        viewModel.fetchForecastList()
    }

}