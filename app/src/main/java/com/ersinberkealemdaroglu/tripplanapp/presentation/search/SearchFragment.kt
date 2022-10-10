package com.ersinberkealemdaroglu.tripplanapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
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
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        searchBottomSheet()
        bottomNavigationVisible()
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

    private fun pushTopDestinationListener() {
        topDestinationsAdapter.setTopDestinationOnClickListener(object :
            MightNeedTheseOnClickListener {
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

    private fun loadingData() {
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

    private fun searchBottomSheet() {
        searchBinding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchBinding.searchBar.setOnEditorActionListener { v, actionId, event ->
                    if (actionId != EditorInfo.IME_ACTION_DONE) {
                        val searchText = s.toString()
                        val action =
                            SearchFragmentDirections.actionSearchFragmentToSearchListFragment(
                                searchText
                            )
                        findNavController().navigate(action)
                        true
                    } else {
                        println("false")
                        false
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun bottomNavigationVisible(){
        //Bottom Navigation VISIBLE
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navbarBlur = requireActivity().findViewById<ImageView>(R.id.background_blur_bottom_navigation)
        navBar.visibility = View.VISIBLE
        navbarBlur.visibility = View.VISIBLE
    }
}