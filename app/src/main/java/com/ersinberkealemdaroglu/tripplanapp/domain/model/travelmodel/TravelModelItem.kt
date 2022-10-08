package com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TravelModelItem constructor(
    val id: String,
    val category: String?,
    val city: String?,
    val country: String?,
    val description: String?,
    val images: List<Image>,
    val isBookmark: Boolean?,
    val title: String?,
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

class ImagesTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Image>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Image>::class.java).toList()
}