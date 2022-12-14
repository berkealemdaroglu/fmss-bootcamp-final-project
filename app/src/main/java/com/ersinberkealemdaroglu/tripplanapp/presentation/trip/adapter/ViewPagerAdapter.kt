package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentItems = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return fragmentItems.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFragments(fragment: Fragment) {
        this.fragmentItems.add(fragment)
        notifyDataSetChanged()
    }
}