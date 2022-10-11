package com.ersinberkealemdaroglu.tripplanapp.presentation.trip

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentTripScreenBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter.ViewPagerAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.bookmark.BookmarkFragment
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark.TripsBookmarkFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TripScreenFragment : Fragment() {
    private lateinit var tripBinding: FragmentTripScreenBinding
    private lateinit var viewPager: ViewPager2
    private val fragmentTitle = ArrayList<String>()
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val tabIcons = intArrayOf(R.drawable.trips_bookmark_icon, R.drawable.bookmark_icon)

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
        viewPagerSetup()
        bottomNavigationVisible()


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init() {
        viewPager = tripBinding.viewPager
    }

    private fun viewPagerSetup() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.setFragments(TripsBookmarkFragment())
        viewPagerAdapter.setFragments(BookmarkFragment())

        tripBinding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        tripBinding.viewPager.adapter = viewPagerAdapter

        fragmentTitle.add("Trips")
        fragmentTitle.add("Bookmarks")

        tabLayout = tripBinding.tabLayout
        TabLayoutMediator(tripBinding.tabLayout, viewPager) { tab, position ->
            tab.text = fragmentTitle[position]
        }.attach()


        tripBinding.tabLayout.getTabAt(0)!!.setIcon(tabIcons[0])
        tripBinding.tabLayout.getTabAt(1)!!.setIcon(tabIcons[1])

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