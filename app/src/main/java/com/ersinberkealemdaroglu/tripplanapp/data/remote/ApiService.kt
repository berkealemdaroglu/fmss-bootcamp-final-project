package com.ersinberkealemdaroglu.tripplanapp.data.remote

import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("AllTravelList")
    fun getBlogData() : Call<TravelModel>

}