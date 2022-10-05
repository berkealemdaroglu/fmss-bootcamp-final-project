package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.DealsItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem

class DealsAdapterViewHolder(private val dealsBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(dealsBinding.root) {

    fun dealsBind(travelModelItem: TravelModelItem) {
        dealsBinding as DealsItemBinding
        dealsBinding.setVariable(BR.blogImagesDeals, travelModelItem)
    }
}