package com.ersinberkealemdaroglu.tripplanapp.presentation.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentTripsBinding

class TripsFragment : Fragment() {

    private lateinit var tripsBinding : FragmentTripsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tripsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_trips, container, false)
        return tripsBinding.root
    }
}