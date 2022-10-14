package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentBookmarkBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.TripScreenFragmentDirections
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter.BookmarkAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkItemOnClickListener
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemRemoveClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private lateinit var bookmarkBinding: FragmentBookmarkBinding
    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private lateinit var bookmarkAdapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bookmarkBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)
        return bookmarkBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        bookmarkAdapter = BookmarkAdapter()
        bookmarkBinding.bookmarkRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        bookmarkBinding.bookmarkRecyclerview.adapter = bookmarkAdapter

        getTravelModelLocalDB()
        setBookmarkOnClickListener()
        deleteTravelModelLocalDB()
    }

    private fun deleteTravelModelLocalDB() {
        getTravelModelLocalDB()
        bookmarkAdapter.setBookmarkRemoveItemClickListener(object :
            BookmarkOnItemRemoveClickListener {
            override fun onClick(travelModelItem: TravelModelItem) {
                bookmarkViewModel.deleteTravelModelLocalDB(travelModelItem.id)
                Toast.makeText(context, "Bookmark Deleted!", Toast.LENGTH_SHORT).show()
                getTravelModelLocalDB()
            }
        })
    }

    private fun getTravelModelLocalDB() {
        bookmarkViewModel.getTravelModelLocalDB()
        bookmarkViewModel.travelModel.observe(viewLifecycleOwner) { travelModelItems ->
            bookmarkAdapter.setBookmarkData(travelModelItems)
        }
    }

    private fun setBookmarkOnClickListener() {
        bookmarkAdapter.setBookmarkItemOnClickListener(object : BookmarkItemOnClickListener {
            override fun onClick(travelModelItem: TravelModelItem) {
                val action = TripScreenFragmentDirections.actionTripScreenFragmentToDetailFragment(
                    travelModelItem
                )
                findNavController().navigate(action)
            }
        })
    }
}