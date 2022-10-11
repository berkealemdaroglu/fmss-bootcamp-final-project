package com.ersinberkealemdaroglu.tripplanapp.presentation.home.viewpagerfragments

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DealsViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentItems = ArrayList<Fragment>()
    val tabLayoutTitle = ArrayList<String>()

    override fun getItemCount(): Int {
        return fragmentItems.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFragmentsItem(fragment: Fragment, tabLayoutTitle: String) {
        this.fragmentItems.add(fragment)
        this.tabLayoutTitle.add(tabLayoutTitle)
        notifyDataSetChanged()
    }


}