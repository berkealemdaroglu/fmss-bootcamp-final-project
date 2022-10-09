package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentTripsBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.bottomsheet.TripBottomSheet
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter.TripBookmarkAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripsBookmarkFragment : Fragment() {

    private lateinit var tripsBinding : FragmentTripsBinding
    private val tripBookmarkViewModel : TripBookmarkViewModel by viewModels()
    private val tripBookmarkAdapter = TripBookmarkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tripsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_trips, container, false)
        return tripsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        tripBottomSheet()
        getAllTripModel()
    }

    private fun init(){
        tripsBinding.tripRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        tripsBinding.tripRecyclerview.adapter = tripBookmarkAdapter
    }

    private fun tripBottomSheet(){
        tripsBinding.floatingActionButton.setOnClickListener {
            val tripBottomSheet = TripBottomSheet()
            tripBottomSheet.show(childFragmentManager, "TAG1")
        }
    }

    private fun getAllTripModel(){
        tripBookmarkViewModel.getAllTripModelLocalDB().observe(viewLifecycleOwner){ tripModel ->
            tripBookmarkAdapter.setTripModel(tripModel)
            println("osman 2" + tripModel.size)
        }
    }

}