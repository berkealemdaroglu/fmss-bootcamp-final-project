package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.toparticlesadapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.databinding.TopPickArticlesItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.guide.GuideFragmentDirections

class BlogDataTopArticlesAdapterViewHolder(private val topArticlesBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(topArticlesBinding.root) {

    fun topArticlesBind(blogDataItem: TravelModelItem) {
        topArticlesBinding as TopPickArticlesItemBinding
        topArticlesBinding.setVariable(BR.blogDataItem, blogDataItem)
        //topArticlesBinding.cardViewImage.apiDownloadFromUri(blogDataItem.images[0].url)

        topArticlesBinding.cardViewImage.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(GuideFragmentDirections.actionGuideFragmentToDetailFragment(blogDataItem))
        }

    }

}