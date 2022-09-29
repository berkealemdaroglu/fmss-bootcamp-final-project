package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel
import com.ersinberkealemdaroglu.tripplanapp.domain.usecase.BlogDataModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideFragmentViewModel @Inject constructor(private val blogDataModelUseCase: BlogDataModelUseCase) : ViewModel() {

    lateinit var getBlogData : LiveData<BlogDataModel>

    init {
        getAllBlogData()
    }

    fun getAllBlogData(){
        blogDataModelUseCase.getBlogData()
        Log.v("VİEWMODEL", "asfdas")
        this.getBlogData = blogDataModelUseCase.blogData
    }

}