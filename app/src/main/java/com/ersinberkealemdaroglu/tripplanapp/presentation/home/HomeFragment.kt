package com.ersinberkealemdaroglu.tripplanapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentHomeBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.DealsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private val dealsAdapter = DealsAdapter()
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

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
        homeBinding.homeRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.homeRecyclerview.adapter = dealsAdapter

        staticHomeButtons()
        dealsAdapter()
    }

    private fun dealsAdapter() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { dealsData ->
            dealsAdapter.setDealsData(dealsData)
        }

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


}