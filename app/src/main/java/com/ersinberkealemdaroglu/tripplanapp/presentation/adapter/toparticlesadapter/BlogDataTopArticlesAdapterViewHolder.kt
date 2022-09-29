package com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.toparticlesadapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.databinding.TopPickArticlesItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.PostsPublish
import com.ersinberkealemdaroglu.tripplanapp.presentation.guide.GuideFragmentDirections

class BlogDataTopArticlesAdapterViewHolder(private val topArticlesBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(topArticlesBinding.root) {

    fun topArticlesBind(blogDataItem: PostsPublish) {
        topArticlesBinding as TopPickArticlesItemBinding
        topArticlesBinding.setVariable(BR.blogDataItem, blogDataItem)

        topArticlesBinding.cardViewImage.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(GuideFragmentDirections.actionGuideFragmentToDetailFragment(blogDataItem))
        }

    }

}