package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModel
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
class GuideFragmentViewModel @Inject constructor(private val blogDataModelUseCase: BlogDataModelUseCase) :
    ViewModel() {

    private var _blogData = MutableLiveData<TravelModel>()
    val blogData: LiveData<TravelModel>
        get() = _blogData

    init {
        getAllBlogData()
    }

    fun getAllBlogData() {
        CoroutineScope(Dispatchers.IO).launch {
            blogDataModelUseCase.getBlogData().enqueue(object : Callback<TravelModel> {
                override fun onResponse(call: Call<TravelModel>, response: Response<TravelModel>) {
                    _blogData.value = response.body()
                }

                override fun onFailure(call: Call<TravelModel>, t: Throwable) {
                    println("Warning!")
                }
            })
        }
    }

}