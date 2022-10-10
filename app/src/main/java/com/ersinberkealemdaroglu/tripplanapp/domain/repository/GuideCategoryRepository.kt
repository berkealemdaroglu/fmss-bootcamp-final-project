package com.ersinberkealemdaroglu.tripplanapp.domain.repository

import com.ersinberkealemdaroglu.tripplanapp.domain.model.guidecategory.GuideCategory
import retrofit2.Call

interface GuideCategoryRepository {

    fun getGuideCategoryModel(): Call<GuideCategory>
}