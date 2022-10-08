package com.ersinberkealemdaroglu.tripplanapp.utils

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem

interface MightNeedTheseOnClickListener {

    fun onClick(travelItem: TravelModelItem)
}