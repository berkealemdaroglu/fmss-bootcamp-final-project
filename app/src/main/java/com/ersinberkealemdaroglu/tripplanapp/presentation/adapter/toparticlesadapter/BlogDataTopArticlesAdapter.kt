package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.toparticlesadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem

class BlogDataTopArticlesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var blogDataModel: TravelModel = TravelModel()
    private var topPickArray = ArrayList<TravelModelItem>()

    private fun categoryFilter() {
        for (travel in blogDataModel) {
            if (travel.category == "toppick") {
                topPickArray.add(travel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val blogDataTopArticlesBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.top_pick_articles_item, parent, false
        )

        return BlogDataTopArticlesAdapterViewHolder(blogDataTopArticlesBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BlogDataTopArticlesAdapterViewHolder).topArticlesBind(topPickArray[position])
    }

    override fun getItemCount(): Int {
        return topPickArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBlogDataModel(blogDataModel: TravelModel) {
        this.blogDataModel = blogDataModel
        categoryFilter()
        notifyDataSetChanged()
    }
}