package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentGuideBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.BlogDataAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.toparticlesadapter.BlogDataTopArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : Fragment() {
    private lateinit var guideBinding: FragmentGuideBinding
    private val guideFragmentViewModel : GuideFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        guideBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_guide, container, false)
        return guideBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBlogDataApi()
        getTopArticlesDataApi()
    }

    private fun getBlogDataApi(){
        guideFragmentViewModel.getBlogData().observe(viewLifecycleOwner){ blogDataApi ->
                val blogDataAdapter = BlogDataAdapter(blogDataApi)
                val blogDataLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                guideBinding.mightNeedRecyclerview.layoutManager = blogDataLayout
                guideBinding.mightNeedRecyclerview.adapter = blogDataAdapter
        }
    }

    private fun getTopArticlesDataApi(){

        guideFragmentViewModel.getBlogData().observe(viewLifecycleOwner){ topArticles ->
            val blogDataTopArticlesAdapter = BlogDataTopArticlesAdapter(topArticles)
            val topArticlesLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            guideBinding.topArticlesRecyclerview.adapter = blogDataTopArticlesAdapter
            guideBinding.topArticlesRecyclerview.layoutManager = topArticlesLayout
        }

    }

}