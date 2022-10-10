package com.ersinberkealemdaroglu.tripplanapp.data.remote.repository

import com.ersinberkealemdaroglu.tripplanapp.data.remote.ApiService
import com.ersinberkealemdaroglu.tripplanapp.domain.model.guidecategory.GuideCategory
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.GuideCategoryRepository
import retrofit2.Call

class GuideCategoryRepositoryImp(private val apiService: ApiService) : GuideCategoryRepository {
    override fun getGuideCategoryModel(): Call<GuideCategory> {
        return apiService.getGuideCategory()
    }

}