package com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModel

class DealsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var travelModel: TravelModel = TravelModel()
    private lateinit var dealsBinding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        dealsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.deals_item,
            parent,
            false
        )

        return DealsAdapterViewHolder(dealsBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DealsAdapterViewHolder).dealsBind(travelModel.get(position))
    }

    override fun getItemCount(): Int {
        return travelModel.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDealsData(travelModel: TravelModel) {
        this.travelModel = travelModel
        notifyDataSetChanged()
    }
}