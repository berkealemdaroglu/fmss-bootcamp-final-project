package com.ersinberkealemdaroglu.tripplanapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.ImagesTypeConverter
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem

@Database(entities = [TravelModelItem::class], version = 1, exportSchema = false)
@TypeConverters(ImagesTypeConverter::class)
abstract class TravelAppDatabase : RoomDatabase() {
    abstract fun travelAppDao(): TravelAppDao
}