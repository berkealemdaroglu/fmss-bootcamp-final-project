package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.GuideItemMightNeedTheseBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel

class BlogDataAdapter(private val blogDataModel: BlogDataModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var blogDataBinding: ViewDataBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        blogDataBinding =
            DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                R.layout.guide_item_might_need_these,
                parent,
                false)
        return BlogDataAdapterViewHolder(blogDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BlogDataAdapterViewHolder).blogDataAdapterBind(blogDataModel.posts_publish[position])
    }

    override fun getItemCount(): Int {
        return blogDataModel.posts_publish.size
    }

/*    @SuppressLint("NotifyDataSetChanged")
    fun setBlogDataModel(blogDataModel: BlogDataModel) {
        this.blogDataModel = blogDataModel
        notifyDataSetChanged()
    }*/
}