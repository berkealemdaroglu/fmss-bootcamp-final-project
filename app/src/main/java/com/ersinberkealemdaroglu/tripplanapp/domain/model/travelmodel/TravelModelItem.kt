package com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TravelModelItem constructor(
    @PrimaryKey
    val id: String,
    val category: String?,
    val city: String?,
    val country: String?,
    val description: String?,
    val images: List<Image>,
    val title: String?,
    val isBookmark: Boolean?
) : Parcelable