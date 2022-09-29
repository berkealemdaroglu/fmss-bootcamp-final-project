package com.ersinberkealemdaroglu.tripplanapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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