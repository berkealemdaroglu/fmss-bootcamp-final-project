package com.ersinberkealemdaroglu.tripplanapp.presentation.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class TopDestinationsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var travelModel: TravelModel = TravelModel()
    private val topDestinationsList = ArrayList<TravelModelItem>()
    private lateinit var topDestinationBinding: ViewDataBinding
    private var listener: MightNeedTheseOnClickListener? = null

    private fun topDestinationCategoryFilter() {
        for (travel in travelModel) {
            if (travel.category == "topdestination") {
                topDestinationsList.add(travel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        topDestinationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.top_destinations_item,
            parent,
            false
        )

        return TopDestinationsAdapterViewHolder(topDestinationBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TopDestinationsAdapterViewHolder).topDestinationBind(
            topDestinationsList[position],
            listener
        )
    }

    override fun getItemCount(): Int {
        return topDestinationsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataTopDestination(travelModel: TravelModel) {
        this.travelModel = travelModel
        notifyDataSetChanged()
        topDestinationCategoryFilter()
    }

    fun setTopDestinationOnClickListener(listener: MightNeedTheseOnClickListener) {
        this.listener = listener
    }
}