package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentGuideBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.PostsPublish
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.BlogDataAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.toparticlesadapter.BlogDataTopArticlesAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class GuideFragment : Fragment() {
    private lateinit var guideBinding: FragmentGuideBinding
    private val guideFragmentViewModel : GuideFragmentViewModel by viewModels()
    private val blogDataAdapter: BlogDataAdapter = BlogDataAdapter()
    private val blogDataTopArticlesAdapter: BlogDataTopArticlesAdapter = BlogDataTopArticlesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        guideBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_guide, container, false)
        return guideBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()
        getBlogDataApi()
        getTopArticlesDataApi()
        seeAllNavigate()
        pushMightNeedThisOnClickController()
    }

    private fun init() {
        guideBinding.mightNeedRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        guideBinding.mightNeedRecyclerview.adapter = blogDataAdapter

        guideBinding.topArticlesRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        guideBinding.topArticlesRecyclerview.adapter = blogDataTopArticlesAdapter

    }

    private fun getBlogDataApi(){
        guideFragmentViewModel.getBlogData.observe(viewLifecycleOwner){ blogDataApi ->
            blogDataAdapter.setBlogDataModel(blogDataApi)
        }
    }

    private fun getTopArticlesDataApi(){
        guideFragmentViewModel.getBlogData.observe(viewLifecycleOwner){ topArticles ->
            blogDataTopArticlesAdapter.setBlogDataModel(topArticles)
        }
    }

    private fun seeAllNavigate(){
        guideBinding.seeAllButton.setOnClickListener {
            findNavController().navigate(GuideFragmentDirections.actionGuideFragmentToGuideSeeAllFragment())
        }
    }

    private fun pushMightNeedThisOnClickController() {
        blogDataAdapter.setMightNeedThisOnClickListener(object : MightNeedTheseOnClickListener {
            override fun onClick(postsPublish: PostsPublish) {
                val action = GuideFragmentDirections.actionGuideFragmentToDetailFragment(postsPublish)
                findNavController().navigate(action)
            }
        })
    }

}