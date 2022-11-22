package com.ersinberkealemdaroglu.tripplanapp.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel.TripModel
import com.ersinberkealemdaroglu.tripplanapp.utils.Converter

@Database(
    version = 2,
    entities = [TripModel::class, TravelModelItem::class],
    exportSchema = true,
)
@TypeConverters(Converter::class)
abstract class TravelAppDatabase : RoomDatabase() {
    abstract fun travelAppDao(): TravelAppDao
}
