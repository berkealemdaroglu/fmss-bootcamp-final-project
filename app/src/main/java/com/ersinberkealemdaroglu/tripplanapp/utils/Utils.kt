package com.ersinberkealemdaroglu.tripplanapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun ImageView.apiDownloadFromUri(url: String?) {

    url.let {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("android:downloadImageUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.apiDownloadFromUri(url)
}


