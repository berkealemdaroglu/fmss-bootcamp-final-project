package com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.transportationsadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.viewholder.DealsAdapterViewHolder

class TransportationsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var travelModel : TravelModel = TravelModel()
    private lateinit var dealsBinding: ViewDataBinding
    private var transportationsArray = ArrayList<TravelModelItem>()

    private fun transportationCategoryFilter(){
        for (transport in travelModel){
            if (transport.category == "transportation"){
                transportationsArray.add(transport)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        dealsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.deals_item, parent, false)

        return DealsAdapterViewHolder(dealsBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DealsAdapterViewHolder).dealsBind(transportationsArray[position])
    }

    override fun getItemCount(): Int {
        return transportationsArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTransportData(travelModel: TravelModel){
        this.travelModel = travelModel
        notifyDataSetChanged()
        transportationCategoryFilter()
    }
}