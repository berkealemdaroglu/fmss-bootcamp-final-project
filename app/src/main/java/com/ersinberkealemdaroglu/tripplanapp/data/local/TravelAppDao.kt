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

    @Query("SELECT * FROM TravelModelItem ORDER BY id DESC")
    suspend fun gelAllTravelModel(): List<TravelModelItem>

/*    @Query("SELECT * FROM TravelModelItem WHERE id = :id")
    suspend fun getModelData(id: String): List<TravelModelItem>*/

    @Query("SELECT EXISTS (SELECT 1 FROM TravelModelItem WHERE id = :id)")
    suspend fun exists(id: String): Boolean

    @Query("DELETE FROM TravelModelItem WHERE id = :id")
    suspend fun delete(id: String)

    @Insert
    suspend fun insertTripModel(vararg tripModel: TripModel): List<Long>

    @Query("SELECT * FROM trip_model ORDER BY uuid DESC")
    fun getAllTripModel(): LiveData<List<TripModel>>

    @Query("SELECT * FROM trip_model WHERE uuid= :uuid")
    suspend fun getTripModel(uuid: Int): TripModel

    @Query("SELECT EXISTS (SELECT 1 FROM trip_model WHERE uuid = :uuid)")
    suspend fun tripModelExists(uuid: Int): Boolean

    @Query("DELETE FROM trip_model WHERE uuid = :uuid")
    suspend fun tripModelDelete(uuid: Int)
}
