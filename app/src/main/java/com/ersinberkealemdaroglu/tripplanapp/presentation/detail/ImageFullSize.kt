package com.ersinberkealemdaroglu.tripplanapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.DetailImageFullSizeBinding

class ImageFullSize : Fragment() {
    private lateinit var imageFullSizeBinding: DetailImageFullSizeBinding
    private val navArgs: ImageFullSizeArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        imageFullSizeBinding =
            DataBindingUtil.inflate(inflater, R.layout.detail_image_full_size, container, false)
        return imageFullSizeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageFullSize()
    }

    /**
     * argumentten gelen verileri xml de tanımladığımız marsData değerine eşitleyerek imageView da tıklanınan görseli gösteriyoruz.
     */
    private fun imageFullSize() {
        navArgs.let { imageFullSize ->
            imageFullSizeBinding.detailItem = imageFullSize.fullImageSize
        }
    }
}