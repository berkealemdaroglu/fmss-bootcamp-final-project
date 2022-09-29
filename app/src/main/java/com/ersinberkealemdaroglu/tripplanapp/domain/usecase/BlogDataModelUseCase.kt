package com.ersinberkealemdaroglu.tripplanapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.BlogDataModel
import com.ersinberkealemdaroglu.tripplanapp.domain.repository.BlogDataModelRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BlogDataModelUseCase @Inject constructor(private val blogDataModelRepository: BlogDataModelRepository) {
    private var _blogData = MutableLiveData<BlogDataModel>()
    val blogData : LiveData<BlogDataModel> = _blogData

    fun getBlogData(){
        blogDataModelRepository.getBlogDataModel().enqueue(object : Callback<BlogDataModel>{
            override fun onResponse(call: Call<BlogDataModel>, response: Response<BlogDataModel>) {
                _blogData.value = response.body()
            }

            override fun onFailure(call: Call<BlogDataModel>, t: Throwable) {
                println("Warning!")
            }
        })
    }

}