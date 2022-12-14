package com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "trip_model")
@Parcelize
data class TripModel(
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "hotel")
    val hotel: String,
    @ColumnInfo(name = "image")
    val image: String
) : Parcelable{
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}