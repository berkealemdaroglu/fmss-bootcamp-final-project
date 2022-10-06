package com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.DealsItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.HomeFragmentDirections

class DealsAdapterViewHolder(private val dealsBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(dealsBinding.root) {

    fun dealsBind(travelModelItem: TravelModelItem) {
        dealsBinding as DealsItemBinding
        dealsBinding.setVariable(BR.blogImagesDeals, travelModelItem)

        dealsBinding.dealsImage.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(travelModelItem)
            Navigation.findNavController(it).navigate(action)
        }
    }
}