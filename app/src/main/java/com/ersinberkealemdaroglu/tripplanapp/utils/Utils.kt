package com.ersinberkealemdaroglu.tripplanapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun ImageView.apiDownloadFromUri(post_excerpt : String?){

    post_excerpt?.let {
        Glide.with(context)
            .load(post_excerpt)
            .into(this)
    }
}

@BindingAdapter("android:downloadImageUrl")
fun downloadImage(view: ImageView, post_excerpt: String?) {
    view.apiDownloadFromUri(post_excerpt)
}