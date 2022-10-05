package com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TravelModelItem(
    val category: String,
    val city: String,
    val country: String,
    val description: String,
    val id: String,
    val images: List<Image>,
    val isBookmark: Boolean,
    val title: String
) : Parcelable