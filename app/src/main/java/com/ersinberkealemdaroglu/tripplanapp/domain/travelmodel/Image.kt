package com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val height: Int,
    val isHeroImage: Boolean,
    val url: String,
    val width: Int
) : Parcelable