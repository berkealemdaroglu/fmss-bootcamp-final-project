package com.ersinberkealemdaroglu.tripplanapp.utils

import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem

interface MightNeedTheseOnClickListener {

    fun onClick(travelItem: TravelModelItem)
}