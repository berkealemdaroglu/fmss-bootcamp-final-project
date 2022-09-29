package com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CcCategory(
    val count: Int,
    val description: String,
    val filter: String,
    val name: String,
    val object_id: Int,
    val parent: Int,
    val slug: String,
    val taxonomy: String,
    val term_group: Int,
    val term_id: Int,
    val term_taxonomy_id: Int
) : Parcelable