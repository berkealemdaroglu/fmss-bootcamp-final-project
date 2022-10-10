package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {

    private val _travelModel = MutableLiveData<List<TravelModelItem>>()
    val travelModel: LiveData<List<TravelModelItem>>
        get() = _travelModel

    init {
        getTravelModelLocalDB()
    }

    fun getTravelModelLocalDB() {
        CoroutineScope(Dispatchers.IO).launch {
            _travelModel.postValue(databaseRepository.getAllBookmark())
        }
    }

/*    fun deleteTravelModelLocalDB(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseRepository.delete(id)
        }
    }*/
}