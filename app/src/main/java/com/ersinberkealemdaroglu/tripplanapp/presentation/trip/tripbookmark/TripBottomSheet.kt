package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.TripBottomSheetBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel.TripModel
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.adapter.ViewPagerAdapter
import com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark.TripBookmarkViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripBottomSheet : BottomSheetDialogFragment() {

    private lateinit var tripBottomSheetBinding: TripBottomSheetBinding
    private val tripBookmarkViewModel: TripBookmarkViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tripBottomSheetBinding =
            DataBindingUtil.inflate(inflater, R.layout.trip_bottom_sheet, container, false)
        return tripBottomSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTripModel()
    }

    private fun setTripModel() {
        tripBottomSheetBinding.addBookmarkButton.setOnClickListener {
            val countryText = tripBottomSheetBinding.countryText.text.toString()
            val cityText = tripBottomSheetBinding.cityText.text.toString()
            if(cityText.isNotEmpty() || countryText.isNotEmpty()){
                tripBookmarkViewModel.setTripModelLocalDB(TripModel(countryText, cityText))
                dismiss()
            }else{
                Toast.makeText(context, "Please fill in the required fields!\n", Toast.LENGTH_SHORT).show()
            }
            
        }
    }

}