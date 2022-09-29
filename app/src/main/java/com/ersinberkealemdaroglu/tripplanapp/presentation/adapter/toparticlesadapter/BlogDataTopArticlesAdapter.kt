package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.toparticlesadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.BlogDataAdapterViewHolder

class BlogDataTopArticlesAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var blogDataModel: BlogDataModel = BlogDataModel(listOf())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val blogDataTopArticlesBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.top_pick_articles_item, parent, false
        )

        return BlogDataTopArticlesAdapterViewHolder(blogDataTopArticlesBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BlogDataTopArticlesAdapterViewHolder).topArticlesBind(blogDataModel.posts_publish[position])
    }

    override fun getItemCount(): Int {
        return blogDataModel.posts_publish.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBlogDataModel(blogDataModel: BlogDataModel) {
        this.blogDataModel = blogDataModel
        notifyDataSetChanged()
    }
}