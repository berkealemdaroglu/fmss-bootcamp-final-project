package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkItemOnClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemRemoveClickListener

class BookmarkAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var bookmarkBinding: ViewDataBinding
    private var travelModel = ArrayList<TravelModelItem>()
    private var bookmarkOnItemRemoveClickListener: BookmarkOnItemRemoveClickListener? = null
    private var bookmarkItemOnClickListener: BookmarkItemOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        bookmarkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.bookmark_item,
            parent,
            false
        )

        return BookmarkAdapterViewHolder(bookmarkBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookmarkAdapterViewHolder).bookmarkBind(
            travelModel[position],
            bookmarkOnItemRemoveClickListener,
            bookmarkItemOnClickListener
        )
    }

    override fun getItemCount(): Int {
        return travelModel.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBookmarkData(travelModelItem: List<TravelModelItem>) {
        this.travelModel.clear()
        this.travelModel.addAll(travelModelItem)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBookmarkItemOnClickListener(bookmarkItemOnClickListener: BookmarkItemOnClickListener) {
        this.bookmarkItemOnClickListener = bookmarkItemOnClickListener
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBookmarkRemoveItemClickListener(bookmarkOnItemRemoveClickListener: BookmarkOnItemRemoveClickListener) {
        this.bookmarkOnItemRemoveClickListener = bookmarkOnItemRemoveClickListener
        notifyDataSetChanged()
    }


}