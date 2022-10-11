package com.ersinberkealemdaroglu.tripplanapp.presentation.home.viewpagerfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentDealsFlightBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.HomeFragmentViewModel
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.flightsadapter.FlightAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsFlightFragment : Fragment() {

    private lateinit var flightBinding: FragmentDealsFlightBinding
    private lateinit var flightAdapter: FlightAdapter
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        flightBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_deals_flight, container, false)
        return flightBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        flightObserve()
    }

    private fun init() {
        flightAdapter = FlightAdapter()
        flightBinding.flightRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        flightBinding.flightRecyclerview.adapter = flightAdapter

    }

    private fun flightObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { flight ->
            flightAdapter.setFlightData(flight)
        }
    }


}