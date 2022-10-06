package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.needblogadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class MightNeedTheseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var blogDataModel: TravelModel = TravelModel()
    private var listener: MightNeedTheseOnClickListener? = null
    private lateinit var blogDataBinding: ViewDataBinding
    private var travelArray = ArrayList<TravelModelItem>()

    private fun categoryFilter() {
        for (travel in blogDataModel) {
            if (travel.category == "mightneed") {
                travelArray.add(travel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        blogDataBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.guide_item_might_need_these,
                parent,
                false
            )

        return BlogDataAdapterViewHolder(blogDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BlogDataAdapterViewHolder).blogDataAdapterBind(
            travelArray[position],
            listener
        )
    }

    override fun getItemCount(): Int {
        return travelArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBlogDataModel(blogDataModel: TravelModel) {
        this.blogDataModel = blogDataModel
        notifyDataSetChanged()
        categoryFilter()
    }

    fun setMightNeedThisOnClickListener(listener: MightNeedTheseOnClickListener) {
        this.listener = listener
    }


}