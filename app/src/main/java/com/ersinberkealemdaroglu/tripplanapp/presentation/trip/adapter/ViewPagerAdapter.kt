package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    private val fragmentItems: ArrayList<Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return fragmentItems.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position]
    }
}