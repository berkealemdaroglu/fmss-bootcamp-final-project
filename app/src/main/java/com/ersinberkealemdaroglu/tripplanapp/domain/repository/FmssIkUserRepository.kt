package com.ersinberkealemdaroglu.tripplanapp.domain.repository

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.users.Users
import retrofit2.Call

interface FmssIkUserRepository {

    fun getFmssIkUsers(): Call<Users>

}