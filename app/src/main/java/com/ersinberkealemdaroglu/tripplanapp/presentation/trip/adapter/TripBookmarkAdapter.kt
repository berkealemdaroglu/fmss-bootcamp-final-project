package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.TripsItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel.TripModel

class TripBookmarkAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var tripBinding: ViewDataBinding
    private var tripModel: ArrayList<TripModel> = ArrayList()

    class TripModelAdapterViewHolder(private val tripBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(tripBinding.root) {
        fun tripModelBind(tripModel: TripModel) {
            tripBinding as TripsItemBinding
            tripBinding.setVariable(BR.TripItem, tripModel)
            tripBinding.nearbyImages.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        tripBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.trips_item,
            parent,
            false
        )

        return TripModelAdapterViewHolder(tripBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TripModelAdapterViewHolder).tripModelBind(tripModel[position])
    }

    override fun getItemCount(): Int {
        return tripModel.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTripModel(tripModel: List<TripModel>) {
        this.tripModel.clear()
        this.tripModel.addAll(tripModel)
        notifyDataSetChanged()
    }

}