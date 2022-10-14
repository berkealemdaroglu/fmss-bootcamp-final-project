package com.ersinberkealemdaroglu.tripplanapp.presentation.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemRemoveClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class NearbyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var travelModel: TravelModel = TravelModel()
    private lateinit var nearbyBinding: ViewDataBinding
    private var detailOnClickListener: MightNeedTheseOnClickListener? = null
    private var bookmarkOnItemRemoveClickListener: BookmarkOnItemRemoveClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        nearbyBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_nearby_item,
            parent,
            false
        )
        return NearbyAdapterViewHolder(nearbyBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NearbyAdapterViewHolder).nearbyBind(travelModel[position], detailOnClickListener, bookmarkOnItemRemoveClickListener)
    }

    override fun getItemCount(): Int {
        return travelModel.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNearbyData(travelModel: TravelModel) {
        this.travelModel = travelModel
        notifyDataSetChanged()
    }

    fun setNearbyOnClickListener(listener: MightNeedTheseOnClickListener) {
        this.detailOnClickListener = listener
    }

    fun setBookmarkOnClickListener(bookmarkOnClick: BookmarkOnItemRemoveClickListener) {
        this.bookmarkOnItemRemoveClickListener = bookmarkOnClick
    }


}