package com.ersinberkealemdaroglu.tripplanapp.utils

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem

interface BookmarkOnItemRemoveClickListener {

    fun onClick(travelModelItem: TravelModelItem)
}