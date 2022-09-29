package com.ersinberkealemdaroglu.tripplanapp.domain.repository

import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel
import retrofit2.Call

interface BlogDataModelRepository {
    fun getBlogDataModel() : Call<BlogDataModel>
}