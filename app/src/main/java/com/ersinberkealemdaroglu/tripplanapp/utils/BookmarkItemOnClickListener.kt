package com.ersinberkealemdaroglu.tripplanapp.utils

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem

interface BookmarkItemOnClickListener {

    fun onClick(travelModelItem: TravelModelItem)
}