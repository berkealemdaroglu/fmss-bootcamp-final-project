package com.ersinberkealemdaroglu.tripplanapp.presentation.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor() : ViewModel() {

    val _verifyId = MutableLiveData<String>()
    //val verifyId : LiveData<String> = _verifyId
}