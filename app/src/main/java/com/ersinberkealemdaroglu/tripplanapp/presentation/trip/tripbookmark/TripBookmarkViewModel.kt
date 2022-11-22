package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel.TripModel
import com.ersinberkealemdaroglu.tripplanapp.domain.usecase.BlogDataModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TripBookmarkViewModel @Inject constructor(private val databaseRepository: DatabaseRepository, private val blogDataModelUseCase: BlogDataModelUseCase) :
    ViewModel() {

    fun getAllTripModelLocalDB(): LiveData<List<TripModel>> {
        return databaseRepository.getAllTripModel()
    }

    fun setTripModelLocalDB(tripModel: TripModel) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseRepository.insertTripModel(tripModel)
        }
    }

    private var _blogData = MutableLiveData<TravelModel>()
    val getBlogData: LiveData<TravelModel> = _blogData

    init {
        getHotelsData()
    }

    private fun getHotelsData() {
        CoroutineScope(Dispatchers.IO).launch {
            blogDataModelUseCase.getBlogData().enqueue(object : Callback<TravelModel> {
                override fun onResponse(call: Call<TravelModel>, response: Response<TravelModel>) {
                    if (response.isSuccessful){
                        _blogData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<TravelModel>, t: Throwable) {
                    println("Warning!")
                }
            })
        }
    }


}
