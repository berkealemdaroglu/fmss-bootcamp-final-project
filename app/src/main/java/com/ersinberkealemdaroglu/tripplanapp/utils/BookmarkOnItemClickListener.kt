package com.ersinberkealemdaroglu.tripplanapp.utils

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem

interface BookmarkOnItemClickListener {

    fun onClick(travelModelItem: TravelModelItem)
}