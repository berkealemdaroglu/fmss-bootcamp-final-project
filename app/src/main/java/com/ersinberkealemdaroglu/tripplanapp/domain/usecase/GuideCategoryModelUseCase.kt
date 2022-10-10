package com.ersinberkealemdaroglu.tripplanapp.domain.usecase

import com.ersinberkealemdaroglu.tripplanapp.domain.model.guidecategory.GuideCategory
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.GuideCategoryRepository
import retrofit2.Call
import javax.inject.Inject

class GuideCategoryModelUseCase @Inject constructor(private val guideCategoryRepository: GuideCategoryRepository) {

    fun getGuideCategory(): Call<GuideCategory> {
        return guideCategoryRepository.getGuideCategoryModel()
    }
}