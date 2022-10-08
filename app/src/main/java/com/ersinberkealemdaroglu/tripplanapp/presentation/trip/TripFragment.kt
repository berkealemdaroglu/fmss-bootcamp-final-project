package com.ersinberkealemdaroglu.tripplanapp.presentation.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentTripScreenBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TripScreenFragment : Fragment() {
    private lateinit var tripBinding: FragmentTripScreenBinding
    private lateinit var viewPager : ViewPager2
    private val fragmentItems = ArrayList<Fragment>()
    private val fragmentTitle = ArrayList<String>()
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tripBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_trip_screen, container, false)
        return tripBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init(){
        viewPager = tripBinding.viewPager
        viewPagerSetup()
    }

    private fun viewPagerSetup(){
        fragmentItems.add(TripsFragment())
        fragmentItems.add(BookmarkFragment())
        fragmentTitle.add("Trips")
        fragmentTitle.add("Bookmarks")

        val viewPagerAdapter = ViewPagerAdapter(fragmentItems, context as AppCompatActivity)
        tripBinding.viewPager.adapter = viewPagerAdapter
        tabLayout = tripBinding.tabLayout

        TabLayoutMediator(tripBinding.tabLayout, viewPager) { tab, position ->
            tab.text = fragmentTitle[position]
        }.attach()
    }


}