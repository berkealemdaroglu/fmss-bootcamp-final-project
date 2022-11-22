package com.ersinberkealemdaroglu.tripplanapp.domain.usecase

import com.ersinberkealemdaroglu.tripplanapp.domain.model.users.Users
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.FmssIkUserRepository
import retrofit2.Call
import javax.inject.Inject

class FmssUserUseCase @Inject constructor(private val repository: FmssIkUserRepository) {

    fun getAllFmssIkUser(): Call<Users> {
        return repository.getFmssIkUsers()
    }
}