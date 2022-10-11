package com.ersinberkealemdaroglu.tripplanapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentHomeBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.viewpagerfragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var dealsViewPagerAdapter: DealsViewPagerAdapter

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
        viewPager = homeBinding.homeViewPager

        staticHomeButtons()
        bottomNavigationVisible()
        dealsViewPagerSetup()
    }

    private fun dealsViewPagerSetup() {
        dealsViewPagerAdapter = DealsViewPagerAdapter(childFragmentManager, lifecycle)
        dealsViewPagerAdapter.setFragmentsItem(DealsAllFragment(), "All")
        dealsViewPagerAdapter.setFragmentsItem(DealsFlightFragment(), "Flight")
        dealsViewPagerAdapter.setFragmentsItem(DealsHotelFragment(), "Hotels")
        dealsViewPagerAdapter.setFragmentsItem(DealsTransportationFragment(), "Transport")

        homeBinding.homeViewPager.adapter = dealsViewPagerAdapter
        homeBinding.homeViewPager.isUserInputEnabled = false

        tabLayout = homeBinding.dealsTabLayout

        TabLayoutMediator(homeBinding.dealsTabLayout, viewPager) { tab, position ->
            tab.text = dealsViewPagerAdapter.tabLayoutTitle[position]
        }.attach()
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

    private fun bottomNavigationVisible() {
        //Bottom Navigation VISIBLE
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navbarBlur =
            requireActivity().findViewById<ImageView>(R.id.background_blur_bottom_navigation)
        navBar.visibility = View.VISIBLE
        navbarBlur.visibility = View.VISIBLE
    }

}