package com.ersinberkealemdaroglu.tripplanapp.presentation.guide.toparticlesadapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.databinding.TopPickArticlesItemBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.guide.GuideFragmentDirections
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemClickListener

class BlogDataTopArticlesAdapterViewHolder(private val topArticlesBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(topArticlesBinding.root) {

    @SuppressLint("ResourceAsColor")
    fun topArticlesBind(
        blogDataItem: TravelModelItem,
        bookmarkOnItemClickListener: BookmarkOnItemClickListener?
    ) {
        topArticlesBinding as TopPickArticlesItemBinding
        topArticlesBinding.setVariable(BR.blogDataItem, blogDataItem)

        topArticlesBinding.cardViewImage.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(GuideFragmentDirections.actionGuideFragmentToDetailFragment(blogDataItem))
        }

        topArticlesBinding.topPickBookmarkButton.setOnClickListener {
            bookmarkOnItemClickListener?.onClick(blogDataItem)

        }

    }

}