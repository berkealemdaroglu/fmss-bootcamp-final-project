package com.ersinberkealemdaroglu.tripplanapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentDetailBinding
import com.ersinberkealemdaroglu.tripplanapp.utils.apiDownloadFromUri

class DetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentDetailBinding
    private val navArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        detailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBlogDataByArgs()
    }

    private fun setBlogDataByArgs() {
        navArgs.let {
            detailBinding.blogDataItem = it.blogData
        }

        navArgs.let {
            detailBinding.imageView.apiDownloadFromUri(it.blogData.images[0].url)
        }
    }

}