package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.TripsBookmarkItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.viewholder.DealsAdapterViewHolder
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemRemoveClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.OnClick

class HotelsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var travelModel: TravelModel = TravelModel()
    private lateinit var tripsBinding: TripsBookmarkItemBinding
    private var hotelsArray = ArrayList<TravelModelItem>()
    private var clickListener: OnClick? = null

    class HotelsAdapterViewHolder(private val binding: TripsBookmarkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(travelModelItem: TravelModelItem,
         onClick: OnClick?) {
            binding.setVariable(BR.tripsBookmark, travelModelItem)

            binding.dealsImage.setOnClickListener {
                onClick?.onClickItem(travelModelItem)
            }
        }
    }

    private fun hotelCategoryFilter() {
        for (hotels in travelModel) {
            if (hotels.category == "hotel") {
                hotelsArray.add(hotels)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        tripsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.trips_bookmark_item,
            parent,
            false
        )

        return HotelsAdapterViewHolder(tripsBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HotelsAdapterViewHolder).bind(hotelsArray[position], clickListener)
    }

    override fun getItemCount(): Int {
        return hotelsArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHotelsData(travelModel: TravelModel) {
        this.travelModel = travelModel
        notifyDataSetChanged()
        hotelCategoryFilter()
    }

    fun setOnClick(bookmarkOnClick: OnClick) {
        this.clickListener = bookmarkOnClick
    }

}