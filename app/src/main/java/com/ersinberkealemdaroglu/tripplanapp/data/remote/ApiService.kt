package com.ersinberkealemdaroglu.tripplanapp.data.remote

import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("wp-content/export.json")
    fun getBlogData() : Call<BlogDataModel>

}