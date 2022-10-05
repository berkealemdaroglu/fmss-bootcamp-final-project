package com.ersinberkealemdaroglu.tripplanapp.presentation.search.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.TopDestinationsItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.apiDownloadFromUri

class TopDestinationsAdapterViewHolder(private val topDestinationBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(topDestinationBinding.root) {

    fun topDestinationBind(travelModelItem: TravelModelItem) {
        topDestinationBinding as TopDestinationsItemBinding
        topDestinationBinding.setVariable(BR.topDestinationItem, travelModelItem)
        topDestinationBinding.needImages.apiDownloadFromUri(travelModelItem.images[0].url)
    }
}