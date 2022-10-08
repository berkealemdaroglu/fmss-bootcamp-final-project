package com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository

import androidx.lifecycle.LiveData
import com.ersinberkealemdaroglu.tripplanapp.data.local.TravelAppDao
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val travelAppDao: TravelAppDao){

    suspend fun addBookmark(travelModelItem: TravelModelItem){
            travelAppDao.insert(travelModelItem)
    }

    suspend fun getAllBookmark() : List<TravelModelItem>{
            return travelAppDao.gelAllTravelModel()
    }

    suspend fun exists(id: String): Boolean{
        return travelAppDao.exists(id)
    }

    suspend fun delete(id: String){
        travelAppDao.delete(id)
    }
}