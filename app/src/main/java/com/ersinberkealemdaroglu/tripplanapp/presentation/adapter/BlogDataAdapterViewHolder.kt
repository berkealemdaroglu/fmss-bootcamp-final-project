package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.GuideItemMightNeedTheseBinding
import com.ersinberkealemdaroglu.tripplanapp.databinding.TopPickArticlesItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.PostsPublish

class BlogDataAdapterViewHolder(private val blogDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(blogDataBinding.root) {

    fun blogDataAdapterBind(blogDataItem: PostsPublish) {
        blogDataBinding as GuideItemMightNeedTheseBinding
        blogDataBinding.setVariable(BR.blogDataItem, blogDataItem)
        blogDataBinding.root.rootView.setOnClickListener {
            it.findNavController().navigate(R.id.action_guideFragment_to_detailFragment)
        }
    }


}