package com.ersinberkealemdaroglu.tripplanapp.utils

import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem

interface OnClick {
    fun onClickItem(travelItem: TravelModelItem)
}