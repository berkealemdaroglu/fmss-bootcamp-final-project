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
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentDealsHotelBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.HomeFragmentViewModel
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.hotelsadapter.HotelsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsHotelFragment : Fragment() {

    private lateinit var hotelBinding: FragmentDealsHotelBinding
    private lateinit var hotelsAdapter: HotelsAdapter
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hotelBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_deals_hotel, container, false)
        return hotelBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        hotelsObserve()
    }

    private fun init() {
        hotelsAdapter = HotelsAdapter()

        hotelBinding.hotelsRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hotelBinding.hotelsRecyclerview.adapter = hotelsAdapter
    }

    private fun hotelsObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { hotels ->
            hotelsAdapter.setHotelsData(hotels)
        }
    }

}