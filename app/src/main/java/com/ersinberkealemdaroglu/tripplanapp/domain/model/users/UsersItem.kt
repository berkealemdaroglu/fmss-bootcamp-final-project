package com.ersinberkealemdaroglu.tripplanapp.domain.model.users

data class UsersItem(
    val address: String,
    val age: String,
    val birthday: String,
    val city: String,
    val country: String,
    val department: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val identityNumber: String,
    val lastName: String,
    val level: String,
    val manager: String,
    val password: Any,
    val phoneNumber: String,
    val photoPath: String,
    val postalCode: String,
    val role: String,
    val salary: Double,
    val startingDateOfEmployment: String,
    val status: Boolean,
    val title: String
)