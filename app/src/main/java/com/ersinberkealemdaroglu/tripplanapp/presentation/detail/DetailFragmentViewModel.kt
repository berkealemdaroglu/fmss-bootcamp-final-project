package com.ersinberkealemdaroglu.tripplanapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    fun addBookmarkLocalDB(travelModelItem: TravelModelItem) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!databaseRepository.exists(travelModelItem.id)) {
                databaseRepository.addBookmark(travelModelItem)
            } else {
                println("zaten ekli osman")
            }
        }
    }

}
