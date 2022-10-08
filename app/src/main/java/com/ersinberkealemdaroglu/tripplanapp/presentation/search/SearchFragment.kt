package com.ersinberkealemdaroglu.tripplanapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentSearchBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.search.adapter.NearbyAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.search.adapter.TopDestinationsAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val topDestinationsAdapter: TopDestinationsAdapter = TopDestinationsAdapter()
    private val nearbyAdapter: NearbyAdapter = NearbyAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        searchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        searchBinding.topDestinationRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchBinding.topDestinationRV.adapter = topDestinationsAdapter

        searchBinding.nearbyRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchBinding.nearbyRecyclerview.adapter = nearbyAdapter

        topDestinationsAdapter()
        nearbyAdapter()
        pushNearbyOnClickListener()
        refreshData()
        loadingData()
        pushTopDestinationListener()
        bookmarkAddLocalDB()
    }

    private fun topDestinationsAdapter() {
        searchViewModel.getBlogData.observe(viewLifecycleOwner) { topDestinations ->
            topDestinationsAdapter.setDataTopDestination(topDestinations)
        }
    }

    private fun nearbyAdapter() {
        searchViewModel.getBlogData.observe(viewLifecycleOwner) { nearbyItem ->
            nearbyAdapter.setNearbyData(nearbyItem)
        }
    }

    private fun pushNearbyOnClickListener() {
        nearbyAdapter.setNearbyOnClickListener(object : MightNeedTheseOnClickListener {
            override fun onClick(travelItem: TravelModelItem) {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(travelItem)
                findNavController().navigate(action)
            }

        })
    }

    private fun pushTopDestinationListener(){
        topDestinationsAdapter.setTopDestinationOnClickListener(object : MightNeedTheseOnClickListener{
            override fun onClick(travelItem: TravelModelItem) {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(travelItem)
                findNavController().navigate(action)
            }
        })
    }

    private fun refreshData() {
        searchBinding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                topDestinationRV.visibility = View.INVISIBLE
                nearbyRecyclerview.visibility = View.INVISIBLE
                searchViewModel.getAllBlogData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun loadingData(){
        searchViewModel.searchLoadData.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                searchBinding.apply {
                    if (it) {
                        searchLoading.visibility = View.VISIBLE
                        topDestinationRV.visibility = View.INVISIBLE
                        nearbyRecyclerview.visibility = View.INVISIBLE
                    } else {
                        topDestinationRV.visibility = View.VISIBLE
                        nearbyRecyclerview.visibility = View.VISIBLE
                        searchLoading.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun bookmarkAddLocalDB() {
        nearbyAdapter.setBookmarkOnClickListener(object : BookmarkOnItemClickListener {
            override fun onClick(travelModelItem: TravelModelItem) {
                searchViewModel.addBookmarkLocalDB(travelModelItem)
            }
        })
    }
}