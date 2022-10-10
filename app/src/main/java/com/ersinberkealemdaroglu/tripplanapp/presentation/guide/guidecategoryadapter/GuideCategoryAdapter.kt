package com.ersinberkealemdaroglu.tripplanapp.presentation.guide.guidecategoryadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.tripplanapp.BR
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.CategoryItemGuideBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.guidecategory.GuideCategory
import com.ersinberkealemdaroglu.tripplanapp.domain.model.guidecategory.GuideCategoryItem

class GuideCategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var guideCategory = GuideCategory()
    private lateinit var guideCategoryBinding: ViewDataBinding

    class GuideCategoryAdapterViewHolder(private val guideCategoryBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(guideCategoryBinding.root) {
        fun guideCategoryBind(guideCategoryItem: GuideCategoryItem) {
            guideCategoryBinding as CategoryItemGuideBinding
            guideCategoryBinding.setVariable(BR.guideCategory, guideCategoryItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        guideCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_item_guide,
            parent,
            false
        )

        return GuideCategoryAdapterViewHolder(guideCategoryBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GuideCategoryAdapterViewHolder).guideCategoryBind(guideCategory[position])
    }

    override fun getItemCount(): Int {
        return guideCategory.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllGuideCategory(guideCategory: GuideCategory) {
        this.guideCategory = guideCategory
        notifyDataSetChanged()

    }
}