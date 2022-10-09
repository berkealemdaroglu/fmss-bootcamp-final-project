package com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TripModel(
    val country: String,
    val city: String,
    //val checkIn: Date,
    //val checkOut: Date,
    //val url: String,
    //val travelNeed : ArrayList<String>?
) : Parcelable{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
