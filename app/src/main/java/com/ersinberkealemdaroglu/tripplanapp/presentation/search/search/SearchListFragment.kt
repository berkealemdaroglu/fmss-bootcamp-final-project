package com.ersinberkealemdaroglu.tripplanapp.presentation.search.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentSearchListBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.search.SearchViewModel
import com.ersinberkealemdaroglu.tripplanapp.utils.MightNeedTheseOnClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListFragment : BottomSheetDialogFragment() {

    private lateinit var searchListBinding: FragmentSearchListBinding
    private val navArgs: SearchListFragmentArgs by navArgs()
    private val searchListAdapter: SearchListAdapter = SearchListAdapter()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_list, container, false)
        return searchListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setSearchListAdapterNavArgs()
    }

    private fun init() {
        searchListBinding.searchListRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchListBinding.searchListRecyclerview.adapter = searchListAdapter
    }

    private fun setSearchListAdapterNavArgs() {

        searchViewModel.getBlogData.observe(viewLifecycleOwner) { data ->
            navArgs.let {
                searchListAdapter.setSearchItemList(data, it.searchString)
                searchListAdapter.setSearchOnClickListener(object : MightNeedTheseOnClickListener {
                    override fun onClick(travelItem: TravelModelItem) {
                        val action =
                            SearchListFragmentDirections.actionSearchListFragmentToDetailFragment(
                                travelItem
                            )
                        findNavController().navigate(action)
                    }
                })
            }

            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    if (searchListBinding.searchListRecyclerview.isEmpty()) {
                        searchListBinding.searchErrorCard.visibility = View.VISIBLE
                        searchListBinding.restartSearchButton.setOnClickListener {
                            dismiss()
                        }
                    }
                }, 1000)
            }
        }

    }


}