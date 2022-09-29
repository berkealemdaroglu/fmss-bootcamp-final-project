package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.GuideItemMightNeedTheseBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.PostsPublish
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class BlogDataAdapterViewHolder(private val blogDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(blogDataBinding.root) {

    fun blogDataAdapterBind(
        blogDataItem: PostsPublish,
        mightNeedTheseOnClickListener: MightNeedTheseOnClickListener?,
    ) {
        blogDataBinding as GuideItemMightNeedTheseBinding
        blogDataBinding.setVariable(BR.blogDataItem, blogDataItem)

        blogDataBinding.root.rootView.setOnClickListener {
            mightNeedTheseOnClickListener?.onClick(blogDataItem)
        }
    }
}