package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentGuideSeeAllBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.blogdatamodel.PostsPublish
import com.ersinberkealemdaroglu.tripplanapp.presentation.adapter.BlogDataAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideSeeAllFragment : Fragment() {
    private lateinit var seeAllBinding: FragmentGuideSeeAllBinding
    private val guideFragmentViewModel: GuideFragmentViewModel by viewModels()
    private val blogDataAdapter: BlogDataAdapter = BlogDataAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        seeAllBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_guide_see_all, container, false)
        return seeAllBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        seeAllGetData()
        pushMightNeedThisOnClickController()
    }

    private fun init() {
        seeAllBinding.seeAllRecyclerview.layoutManager = GridLayoutManager(context, 3)
        seeAllBinding.seeAllRecyclerview.adapter = blogDataAdapter
    }

    private fun seeAllGetData() {
        guideFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { blogDataApi ->
            blogDataAdapter.setBlogDataModel(blogDataApi)
        }
    }

    private fun pushMightNeedThisOnClickController() {
        blogDataAdapter.setMightNeedThisOnClickListener(object : MightNeedTheseOnClickListener {
            override fun onClick(postsPublish: PostsPublish) {
                val action = GuideSeeAllFragmentDirections.actionGuideSeeAllFragmentToDetailFragment(postsPublish)
                findNavController().navigate(action)
            }
        })
    }

}