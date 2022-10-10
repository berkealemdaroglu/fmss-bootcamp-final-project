package com.ersinberkealemdaroglu.tripplanapp.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentGuideSeeAllBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.guide.needblogadapter.MightNeedTheseAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideSeeAllFragment : Fragment() {

    private val guideFragmentViewModel: GuideFragmentViewModel by activityViewModels()
    private lateinit var seeAllBinding: FragmentGuideSeeAllBinding
    private lateinit var mightNeedTheseAdapter: MightNeedTheseAdapter

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
        bottomNavigationInvisible()
    }

    private fun init() {
        mightNeedTheseAdapter = MightNeedTheseAdapter()
        seeAllBinding.seeAllRecyclerview.layoutManager = GridLayoutManager(context, 3)
        seeAllBinding.seeAllRecyclerview.adapter = mightNeedTheseAdapter
    }

    private fun seeAllGetData() {
        guideFragmentViewModel.blogData.observe(viewLifecycleOwner) { blogDataApi ->
            mightNeedTheseAdapter.setBlogDataModel(blogDataApi)
        }
    }

    private fun pushMightNeedThisOnClickController() {
        mightNeedTheseAdapter.setMightNeedThisOnClickListener(object :
            MightNeedTheseOnClickListener {
            override fun onClick(travelItem: TravelModelItem) {
                val action =
                    GuideSeeAllFragmentDirections.actionGuideSeeAllFragmentToDetailFragment(
                        travelItem
                    )
                findNavController().navigate(action)
            }
        })
    }

    private fun bottomNavigationInvisible(){
        //Bottom Navigation INVISIBLE
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navbarBlur = requireActivity().findViewById<ImageView>(R.id.background_blur_bottom_navigation)
        navBar.visibility = View.INVISIBLE
        navbarBlur.visibility = View.INVISIBLE
    }

}