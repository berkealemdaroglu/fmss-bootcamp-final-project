package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentGuideBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.needblogadapter.MightNeedTheseAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.toparticlesadapter.BlogDataTopArticlesAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : Fragment() {

    private lateinit var guideBinding: FragmentGuideBinding
    private val guideFragmentViewModel: GuideFragmentViewModel by activityViewModels()
    private lateinit var mightNeedTheseAdapter: MightNeedTheseAdapter
    private lateinit var blogDataTopArticlesAdapter: BlogDataTopArticlesAdapter

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
        mightNeedTheseAdapter = MightNeedTheseAdapter()
        guideBinding.mightNeedRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        guideBinding.mightNeedRecyclerview.adapter = mightNeedTheseAdapter

        blogDataTopArticlesAdapter = BlogDataTopArticlesAdapter()
        guideBinding.topArticlesRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        guideBinding.topArticlesRecyclerview.adapter = blogDataTopArticlesAdapter

    }

    private fun getBlogDataApi() {
        guideFragmentViewModel.blogData.observe(viewLifecycleOwner) { getBlogData ->
            mightNeedTheseAdapter.setBlogDataModel(getBlogData)
        }
    }

    private fun getTopArticlesDataApi() {
        guideFragmentViewModel.blogData.observe(viewLifecycleOwner) { topArticles ->
            blogDataTopArticlesAdapter.setBlogDataModel(topArticles)
        }
    }

    private fun seeAllNavigate() {
        guideBinding.seeAllButton.setOnClickListener {
            findNavController().navigate(GuideFragmentDirections.actionGuideFragmentToGuideSeeAllFragment())
        }
    }

    private fun pushMightNeedThisOnClickController() {
        mightNeedTheseAdapter.setMightNeedThisOnClickListener(object : MightNeedTheseOnClickListener {
            override fun onClick(travelItem: TravelModelItem) {
                val action = GuideFragmentDirections.actionGuideFragmentToDetailFragment(travelItem)
                findNavController().navigate(action)
            }
        })
    }

}