package com.ersinberkealemdaroglu.tripplanapp.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentHomeBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.alladapter.AllItemAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.flightsadapter.FlightAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.hotelsadapter.HotelsAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.transportationsadapter.TransportationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var allItemAdapter: AllItemAdapter
    private lateinit var flightAdapter: FlightAdapter
    private lateinit var hotelsAdapter: HotelsAdapter
    private lateinit var transportationsAdapter: TransportationsAdapter
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private var concatAdapter = ConcatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        allItemAdapter = AllItemAdapter()
        flightAdapter = FlightAdapter()
        hotelsAdapter = HotelsAdapter()
        transportationsAdapter = TransportationsAdapter()
        homeBinding.homeRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.homeRecyclerview.adapter = allItemAdapter

        concatAdapter()
        allItemObserve()
        flightObserve()
        hotelsObserve()
        transportObserve()
        staticHomeButtons()
        refreshData()
        loadingData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun allItemObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { allItem ->
            allItemAdapter.setAllData(allItem)
            concatAdapter.notifyDataSetChanged()
            homeBinding.allButton.setOnClickListener {
                concatAdapter.addAdapter(allItemAdapter)
                concatAdapter.notifyDataSetChanged()
                concatAdapter.removeAdapter(flightAdapter)
                concatAdapter.removeAdapter(transportationsAdapter)
                concatAdapter.removeAdapter(hotelsAdapter)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun flightObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { flight ->
            flightAdapter.setFlightData(flight)
            homeBinding.flightsButton.setOnClickListener {
                concatAdapter.addAdapter(flightAdapter)
                concatAdapter.notifyDataSetChanged()
                concatAdapter.removeAdapter(allItemAdapter)
                concatAdapter.removeAdapter(transportationsAdapter)
                concatAdapter.removeAdapter(hotelsAdapter)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun transportObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { transport ->
            transportationsAdapter.setTransportData(transport)
            homeBinding.transportButton.setOnClickListener {
                concatAdapter.addAdapter(transportationsAdapter)
                concatAdapter.notifyDataSetChanged()
                concatAdapter.removeAdapter(allItemAdapter)
                concatAdapter.removeAdapter(flightAdapter)
                concatAdapter.removeAdapter(hotelsAdapter)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun hotelsObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { hotels ->
            hotelsAdapter.setHotelsData(hotels)
            homeBinding.hotelButton.setOnClickListener {
                concatAdapter.addAdapter(hotelsAdapter)
                concatAdapter.notifyDataSetChanged()
                concatAdapter.removeAdapter(allItemAdapter)
                concatAdapter.removeAdapter(transportationsAdapter)
                concatAdapter.removeAdapter(flightAdapter)
            }
        }
    }

    private fun concatAdapter() {
        concatAdapter = ConcatAdapter(
            allItemAdapter
        )
        homeBinding.homeRecyclerview.adapter = concatAdapter
    }


    private fun staticHomeButtons() {
        homeBinding.flightButton.setOnClickListener {
            Toast.makeText(context, "flight button push", Toast.LENGTH_SHORT).show()
        }

        homeBinding.hotelsButton.setOnClickListener {
            Toast.makeText(context, "Hotels button push", Toast.LENGTH_SHORT).show()
        }

        homeBinding.carsButton.setOnClickListener {
            Toast.makeText(context, "Car button push", Toast.LENGTH_SHORT).show()
        }

        homeBinding.taxiButton.setOnClickListener {
            Toast.makeText(context, "Taxi button push", Toast.LENGTH_SHORT).show()
        }
    }

    private fun refreshData() {
        homeBinding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                homeRecyclerview.visibility = View.INVISIBLE
                homeFragmentViewModel.getAllBlogData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun loadingData(){
        homeFragmentViewModel.homeLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                homeBinding.apply {
                    if (it) {
                        homeLoading.visibility = View.VISIBLE
                        homeRecyclerview.visibility = View.INVISIBLE
                    } else {
                        homeRecyclerview.visibility = View.VISIBLE
                        homeLoading.visibility = View.GONE
                    }
                }
            }
        }
    }

}