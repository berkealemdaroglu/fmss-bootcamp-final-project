package com.ersinberkealemdaroglu.tripplanapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ersinberkealemdaroglu.tripplanapp.R

fun ImageView.apiDownloadFromUri(url: String?) {

    url.let {
        Glide.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation_items)
                    .error(R.drawable.error_icon)
            )
            .into(this)
    }
}

@BindingAdapter("android:downloadImageUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.apiDownloadFromUri(url)
}


