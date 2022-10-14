package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel.TripModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripBookmarkViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {

    fun getAllTripModelLocalDB(): LiveData<List<TripModel>> {
        return databaseRepository.getAllTripModel()
    }

    fun setTripModelLocalDB(tripModel: TripModel) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseRepository.insertTripModel(tripModel)
        }
    }
}
