package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.BookmarkItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkItemOnClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemRemoveClickListener

class BookmarkAdapterViewHolder(private val bookmarkBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(bookmarkBinding.root) {

    fun bookmarkBind(
        travelModelItem: TravelModelItem,
        bookmarkOnItemRemoveClickListener: BookmarkOnItemRemoveClickListener?,
        bookmarkItemOnClickListener: BookmarkItemOnClickListener?
    ) {
        bookmarkBinding as BookmarkItemBinding
        bookmarkBinding.setVariable(BR.bookmarkItem, travelModelItem)

        bookmarkBinding.bookmarkButton.setOnClickListener {
            bookmarkOnItemRemoveClickListener?.onClick(travelModelItem)
        }

        bookmarkBinding.card.setOnClickListener {
            bookmarkItemOnClickListener?.onClick(travelModelItem)
        }

    }
}