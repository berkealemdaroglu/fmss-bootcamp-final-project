package com.ersinberkealemdaroglu.tripplanapp.domain.usecase

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.BlogDataModelRepository
import retrofit2.Call
import javax.inject.Inject

class BlogDataModelUseCase @Inject constructor(private val blogDataModelRepository: BlogDataModelRepository) {

    fun getBlogData(): Call<TravelModel> {
        return blogDataModelRepository.getBlogDataModel()
    }
}