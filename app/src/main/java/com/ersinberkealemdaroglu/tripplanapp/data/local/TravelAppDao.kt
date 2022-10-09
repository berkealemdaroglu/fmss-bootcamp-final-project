package com.ersinberkealemdaroglu.tripplanapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel.TripModel

@Dao
interface TravelAppDao {
    @Insert
    suspend fun insert(vararg travelModelItem: TravelModelItem)

    @Query("SELECT * FROM TRAVELMODELITEM ORDER BY uuid DESC")
    suspend fun gelAllTravelModel(): List<TravelModelItem>

    @Query("SELECT * FROM TRAVELMODELITEM WHERE uuid = :uuid")
    suspend fun getModelData(uuid: Int): TravelModelItem

    @Query("SELECT EXISTS (SELECT 1 FROM TRAVELMODELITEM WHERE id = :id)")
    suspend fun exists(id: String): Boolean

    @Query("DELETE FROM TRAVELMODELITEM WHERE id = :id")
    suspend fun delete(id: String)

    @Insert
    suspend fun insertTripModel(vararg tripModel: TripModel)

    @Query("SELECT * FROM TRIPMODEL ORDER BY id DESC")
    fun getAllTripModel(): LiveData<List<TripModel>>

    @Query("SELECT * FROM TRIPMODEL WHERE id= :id")
    suspend fun getTripModel(id: Int): TripModel

    @Query("SELECT EXISTS (SELECT 1 FROM TRIPMODEL WHERE id = :id)")
    suspend fun tripModelExists(id: Int): Boolean
}
