package com.ersinberkealemdaroglu.tripplanapp.data.remote.repository

import com.ersinberkealemdaroglu.tripplanapp.data.remote.ApiService
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.BlogDataModelRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModel
import retrofit2.Call

class BlogDataModelRepositoryImp(private val apiService: ApiService) : BlogDataModelRepository {

    override fun getBlogDataModel(): Call<TravelModel> {
        return apiService.getBlogData()
    }

}