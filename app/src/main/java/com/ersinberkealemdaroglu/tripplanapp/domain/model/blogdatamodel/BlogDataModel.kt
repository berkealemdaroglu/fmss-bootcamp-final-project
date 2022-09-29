package com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.properties.ReadWriteProperty

@Parcelize
data class BlogDataModel(
    val posts_publish: List<PostsPublish>,
) : Parcelable