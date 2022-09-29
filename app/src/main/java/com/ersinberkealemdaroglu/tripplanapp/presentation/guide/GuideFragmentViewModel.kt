package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel
import com.ersinberkealemdaroglu.tripplanapp.domain.usecase.BlogDataModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideFragmentViewModel @Inject constructor(private val blogDataModelUseCase: BlogDataModelUseCase) : ViewModel() {

    fun getBlogData() : LiveData<BlogDataModel>{
        blogDataModelUseCase.apply {
            getBlogData()
            return blogData
        }
    }

}