package com.ersinberkealemdaroglu.tripplanapp.presentation.guide.needblogadapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.databinding.GuideItemMightNeedTheseBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener

class BlogDataAdapterViewHolder(private val blogDataBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(blogDataBinding.root) {

    fun blogDataAdapterBind(
        blogDataItem: TravelModelItem,
        mightNeedTheseOnClickListener: MightNeedTheseOnClickListener?,
    ) {
        blogDataBinding as GuideItemMightNeedTheseBinding

        blogDataBinding.setVariable(BR.needTheseModel, blogDataItem)

        blogDataBinding.root.rootView.setOnClickListener {
            mightNeedTheseOnClickListener?.onClick(blogDataItem)
        }
    }
}