package com.ersinberkealemdaroglu.tripplanapp.presentation.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentBookmarkBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter.BookmarkAdapter
import com.ersinberkealemdaroglu.tripplanapp.utils.BookmarkOnItemClickListener
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
        getTravelModelLocalDB()
    }

    private fun init() {
        bookmarkAdapter = BookmarkAdapter()
        bookmarkBinding.bookmarkRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        bookmarkBinding.bookmarkRecyclerview.adapter = bookmarkAdapter


    }

    private fun getTravelModelLocalDB() {
        bookmarkViewModel.travelModel.observeForever { travelModelItems ->
            bookmarkAdapter.setBookmarkData(travelModelItems)
            bookmarkViewModel.getTravelModelLocalDB()
        }
    }

    /**
     * TODO delete eksik
     */
/*    private fun deleteTravelModelLocalDB() {
        bookmarkAdapter.setBookmarkItemOnClickListener(object : BookmarkOnItemClickListener {
            override fun onClick(travelModelItem: TravelModelItem) {
                bookmarkViewModel.deleteTravelModelLocalDB(travelModelItem.id)
                bookmarkViewModel.getTravelModelLocalDB()
                Toast.makeText(context, "Bookmark Deleted", Toast.LENGTH_SHORT).show()
            }
        })
    }*/


    /**
     * TODO onclik eksik
     */
/*    private fun setBookmarkOnClickListener(){
        bookmarkAdapter.setBookmarkOnClickListener(object : MightNeedTheseOnClickListener{
            override fun onClick(travelItem: TravelModelItem) {
                val action = BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(travelItem)
                findNavController().navigate(action)
            }
        })
    }*/
}