package com.ersinberkealemdaroglu.tripplanapp.data.remote.repository

import com.ersinberkealemdaroglu.tripplanapp.data.remote.ApiService
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.users.Users
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.BlogDataModelRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.FmssIkUserRepository
import retrofit2.Call

class FmssIkUsersImp (private val apiService: ApiService) : FmssIkUserRepository {

    override fun getFmssIkUsers(): Call<Users> {
        return apiService.getUsersFmssIk()
    }


}