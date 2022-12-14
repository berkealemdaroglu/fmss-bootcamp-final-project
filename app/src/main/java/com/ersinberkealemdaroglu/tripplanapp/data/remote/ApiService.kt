package com.ersinberkealemdaroglu.tripplanapp.data.remote

import com.ersinberkealemdaroglu.tripplanapp.domain.model.guidecategory.GuideCategory
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.users.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("AllTravelList")
    fun getBlogData(): Call<TravelModel>

    @GET("GuideCategories")
    fun getGuideCategory(): Call<GuideCategory>

    @GET("users")
    fun getUsersFmssIk(): Call<Users>

}