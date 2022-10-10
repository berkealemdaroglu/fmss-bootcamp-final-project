package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.model.guidecategory.GuideCategory
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModel
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.domain.usecase.BlogDataModelUseCase
import com.ersinberkealemdaroglu.tripplanapp.domain.usecase.GuideCategoryModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class GuideFragmentViewModel @Inject constructor(
    private val blogDataModelUseCase: BlogDataModelUseCase,
    private val databaseRepository: DatabaseRepository,
    private val guideCategoryModelUseCase: GuideCategoryModelUseCase
) :
    ViewModel() {

    private val _guideDataLoading = MutableLiveData<Boolean>()
    val guideLoading: LiveData<Boolean> = _guideDataLoading

    private val _guideCategory = MutableLiveData<GuideCategory>()
    val guideCategory : LiveData<GuideCategory> = _guideCategory

    private var _blogData = MutableLiveData<TravelModel>()
    val blogData: LiveData<TravelModel>
        get() = _blogData

    init {
        getAllBlogData()
        getAllGuideCategoryData()
    }

    fun getAllBlogData() {
        _guideDataLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            blogDataModelUseCase.getBlogData().enqueue(object : Callback<TravelModel> {
                override fun onResponse(call: Call<TravelModel>, response: Response<TravelModel>) {
                    _blogData.value = response.body()
                    _guideDataLoading.postValue(false)
                }

                override fun onFailure(call: Call<TravelModel>, t: Throwable) {
                    println("Warning!")
                    _guideDataLoading.postValue(true)
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

    fun getAllGuideCategoryData(){
        guideCategoryModelUseCase.getGuideCategory().enqueue(object : Callback<GuideCategory>{
            override fun onResponse(call: Call<GuideCategory>, response: Response<GuideCategory>) {
                _guideCategory.value = response.body()
            }

            override fun onFailure(call: Call<GuideCategory>, t: Throwable) {
                println("hata var")
            }
        })
    }
}