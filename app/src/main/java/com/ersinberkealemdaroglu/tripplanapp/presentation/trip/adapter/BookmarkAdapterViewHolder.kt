package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.SearchNearbyItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class BookmarkAdapterViewHolder(private val bookmarkBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(bookmarkBinding.root) {

    fun bookmarkBind(
        travelModelItem: TravelModelItem, bookmarkOnItemClickListener: BookmarkOnItemClickListener?,
        mightNeedTheseOnClickListener: MightNeedTheseOnClickListener?
    ) {
        bookmarkBinding as SearchNearbyItemBinding
        bookmarkBinding.setVariable(BR.nearbyItem, travelModelItem)

        bookmarkBinding.bookmarkButton.setOnClickListener {
            println("view holder girdi")
            bookmarkOnItemClickListener?.onClick(travelModelItem)
        }

        bookmarkBinding.nearbyImages.setOnClickListener {
            mightNeedTheseOnClickListener?.onClick(travelModelItem)
        }
    }
}