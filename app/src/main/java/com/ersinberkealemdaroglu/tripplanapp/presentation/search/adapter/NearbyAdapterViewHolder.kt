package com.ersinberkealemdaroglu.tripplanapp.presentation.search.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.SearchNearbyItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class NearbyAdapterViewHolder(private val nearbyBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(nearbyBinding.root) {

    fun nearbyBind(
        travelModelItem: TravelModelItem,
        mightNeedTheseOnClickListener: MightNeedTheseOnClickListener?
    ) {
        nearbyBinding as SearchNearbyItemBinding
        nearbyBinding.setVariable(BR.nearbyItem, travelModelItem)

        nearbyBinding.root.rootView.setOnClickListener {
            mightNeedTheseOnClickListener?.onClick(travelModelItem)
        }
    }


}