package com.ersinberkealemdaroglu.tripplanapp.data.remote.repository

import com.ersinberkealemdaroglu.tripplanapp.data.remote.ApiService
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.BlogDataModelRepository
import retrofit2.Call

class BlogDataModelRepositoryImp(private val apiService: ApiService) : BlogDataModelRepository {

    override fun getBlogDataModel(): Call<BlogDataModel> {
        return apiService.getBlogData()
    }

}