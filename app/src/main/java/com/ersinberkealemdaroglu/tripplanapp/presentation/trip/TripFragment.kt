package com.ersinberkealemdaroglu.tripplanapp.presentation.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentTripScreenBinding

class TripScreenFragment : Fragment() {
    private lateinit var tripBinding: FragmentTripScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tripBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_trip_screen, container, false)
        return tripBinding.root
    }

}