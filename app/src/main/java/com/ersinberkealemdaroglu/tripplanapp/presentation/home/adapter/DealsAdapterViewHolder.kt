package com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.databinding.DealsItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.apiDownloadFromUri

class DealsAdapterViewHolder(private val dealsBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(dealsBinding.root) {

    fun dealsBind(travelModelItem: TravelModelItem) {
        dealsBinding as DealsItemBinding
        dealsBinding.dealsImage.apiDownloadFromUri(travelModelItem.images[0].url)
    }
}