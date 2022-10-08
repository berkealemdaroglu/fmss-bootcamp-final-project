package com.ersinberkealemdaroglu.tripplanapp.domain.repository

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import retrofit2.Call

interface BlogDataModelRepository {
    fun getBlogDataModel(): Call<TravelModel>
}