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
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentDealsTransportationBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.HomeFragmentViewModel
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.transportationsadapter.TransportationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsTransportationFragment : Fragment() {
    private lateinit var transportationBinding: FragmentDealsTransportationBinding
    private lateinit var transportationsAdapter: TransportationsAdapter
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        transportationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_deals_transportation,
            container,
            false
        )
        return transportationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        transportObserve()
    }

    private fun init() {
        transportationsAdapter = TransportationsAdapter()

        transportationBinding.dealsRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        transportationBinding.dealsRecyclerview.adapter = transportationsAdapter
    }

    private fun transportObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { transport ->
            transportationsAdapter.setTransportData(transport)
        }
    }

}