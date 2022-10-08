package com.ersinberkealemdaroglu.tripplanapp.di.local

import android.content.Context
import androidx.room.Room
import com.ersinberkealemdaroglu.tripplanapp.data.local.TravelAppDao
import com.ersinberkealemdaroglu.tripplanapp.data.local.TravelAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class DatabaseModule {

    @Provides
    fun provideTravelDao(travelAppDatabase: TravelAppDatabase): TravelAppDao {
        return travelAppDatabase.travelAppDao()
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): TravelAppDatabase {
        return Room.databaseBuilder(
            appContext,
            TravelAppDatabase::class.java,
            "TravelAppDatabase"
        ).build()
    }
}