package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class BookmarkAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var bookmarkBinding: ViewDataBinding
    private var travelModel: ArrayList<TravelModelItem> = ArrayList()
    private var bookmarkOnItemClickListener : BookmarkOnItemClickListener? = null
    private var mightNeedTheseOnClickListener : MightNeedTheseOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        bookmarkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_nearby_item,
            parent,
            false
        )

        return BookmarkAdapterViewHolder(bookmarkBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookmarkAdapterViewHolder).bookmarkBind(travelModel[position], bookmarkOnItemClickListener, mightNeedTheseOnClickListener)
    }

    override fun getItemCount(): Int {
        return travelModel.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBookmarkData(travelModelItems: List<TravelModelItem>){
        this.travelModel.clear()
        this.travelModel.addAll(travelModelItems)
        notifyDataSetChanged()
    }

    fun setBookmarkItemOnClickListener(bookmarkOnItemClickListener: BookmarkOnItemClickListener){
        this.bookmarkOnItemClickListener = bookmarkOnItemClickListener
    }

    fun setBookmarkOnClickListener(listener: MightNeedTheseOnClickListener) {
        this.mightNeedTheseOnClickListener = listener
    }

}