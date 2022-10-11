package com.ersinberkealemdaroglu.tripplanapp.presentation.home.viewpagerfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentDealsAllBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.HomeFragmentViewModel
import com.ersinberkealemdaroglu.tripplanapp.presentation.home.adapter.alladapter.AllItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsAllFragment : Fragment() {

    private lateinit var allItemAdapter: AllItemAdapter
    private lateinit var allBinding : FragmentDealsAllBinding
    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_deals_all, container, false)
        return allBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        allItemObserve()
    }

    private fun init(){
        allItemAdapter = AllItemAdapter()

        allBinding.allRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        allBinding.allRecyclerview.adapter = allItemAdapter
    }

    private fun allItemObserve() {
        homeFragmentViewModel.getBlogData.observe(viewLifecycleOwner) { allItem ->
            allItemAdapter.setAllData(allItem)
        }
    }

}