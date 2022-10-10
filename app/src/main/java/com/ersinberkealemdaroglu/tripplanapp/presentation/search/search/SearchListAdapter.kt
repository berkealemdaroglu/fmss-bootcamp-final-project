package com.ersinberkealemdaroglu.tripplanapp.presentation.search.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.SearchNearbyItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import java.util.*

class SearchListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var searchListBinding: ViewDataBinding
    private var travelModel: TravelModel = TravelModel()
    private val searchListArray = ArrayList<TravelModelItem>()
    private var searchOnClickListener: MightNeedTheseOnClickListener? = null


    class SearchListAdapterViewHolder(private val searchListBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(searchListBinding.root) {
        fun searchListBind(
            travelModelItem: TravelModelItem,
            mightNeedTheseOnClickListener: MightNeedTheseOnClickListener
        ) {
            searchListBinding as SearchNearbyItemBinding
            searchListBinding.setVariable(BR.nearbyItem, travelModelItem)
            searchListBinding.nearbyImages.setOnClickListener {
                mightNeedTheseOnClickListener.onClick(travelModelItem)
            }

        }
    }

    private fun searchFilter(searchText: String) {
        for (search in travelModel) {
            when {
                search.description!!.lowercase(Locale.ROOT)
                    .contains(searchText) -> searchListArray.add(search)
                search.title!!.lowercase(Locale.ROOT).contains(searchText) -> searchListArray.add(
                    search
                )
                search.country!!.lowercase(Locale.ROOT).contains(searchText) -> searchListArray.add(
                    search
                )
                search.city!!.lowercase(Locale.ROOT).contains(searchText) -> searchListArray.add(
                    search
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        searchListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_nearby_item,
            parent,
            false
        )

        return SearchListAdapterViewHolder(searchListBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        searchOnClickListener?.let {
            (holder as SearchListAdapterViewHolder).searchListBind(searchListArray[position], it)
        }
    }

    override fun getItemCount(): Int {
        return searchListArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSearchItemList(travelModel: TravelModel, searchText: String) {
        this.searchListArray.clear()
        this.travelModel = travelModel
        notifyDataSetChanged()
        searchFilter(searchText)

    }

    fun setSearchOnClickListener(listener: MightNeedTheseOnClickListener) {
        this.searchOnClickListener = listener
    }
}