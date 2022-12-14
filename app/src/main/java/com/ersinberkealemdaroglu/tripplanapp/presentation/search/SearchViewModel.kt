package com.ersinberkealemdaroglu.tripplanapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
//import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.domain.usecase.BlogDataModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val blogDataModelUseCase: BlogDataModelUseCase,
    private val databaseRepository: DatabaseRepository
) :
    ViewModel() {

    private val _searchLoadData = MutableLiveData<Boolean>()
    val searchLoadData: LiveData<Boolean> = _searchLoadData

    private val _nearbyData = MutableLiveData<TravelModel>()
    val nearbyData : LiveData<TravelModel> = _nearbyData

    private var _blogData = MutableLiveData<TravelModel>()
    val getBlogData: LiveData<TravelModel> = _blogData

    init {
        getAllBlogData()
        getNearbyData()
    }

    fun getAllBlogData() {
        _searchLoadData.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            blogDataModelUseCase.getBlogData().enqueue(object : Callback<TravelModel> {
                override fun onResponse(call: Call<TravelModel>, response: Response<TravelModel>) {
                    _blogData.value = response.body()
                    _searchLoadData.postValue(false)
                }

                override fun onFailure(call: Call<TravelModel>, t: Throwable) {
                    _searchLoadData.postValue(true)
                }
            })
        }
    }

    private fun getNearbyData(){

        CoroutineScope(Dispatchers.IO).launch {
            //delay(1000)
            blogDataModelUseCase.getBlogData().enqueue(object : Callback<TravelModel>{
                override fun onResponse(call: Call<TravelModel>, response: Response<TravelModel>) {
                    _nearbyData.value = response.body()
                }

                override fun onFailure(call: Call<TravelModel>, t: Throwable) {
                }
            })
        }
    }

    fun addBookmarkLocalDB(travelModelItem: TravelModelItem) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!databaseRepository.exists(travelModelItem.id)) {
                databaseRepository.addBookmark(travelModelItem)
            }
        }
    }
}