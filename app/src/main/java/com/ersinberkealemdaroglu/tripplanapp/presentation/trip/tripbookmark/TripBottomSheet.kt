
package com.ersinberkealemdaroglu.tripplanapp.presentation.trip.tripbookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersinberkealemdaroglu.datapicker.DataPicker
import com.ersinberkealemdaroglu.datapicker.OnDataSelectedListener
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.TripBottomSheetBinding
import com.ersinberkealemdaroglu.tripplanapp.domain.model.travelmodel.TravelModelItem
import com.ersinberkealemdaroglu.tripplanapp.domain.model.tripmodel.TripModel
import com.ersinberkealemdaroglu.tripplanapp.utils.OnClick
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TripBottomSheet : BottomSheetDialogFragment() {

    private lateinit var tripBottomSheetBinding: TripBottomSheetBinding
    private val tripBookmarkViewModel: TripBookmarkViewModel by viewModels()
    private var myDate: String = ""
    private lateinit var hotelsAdapter: HotelsAdapter
    private var image: String = ""


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

        init()
    }

    private fun init(){
        hotelsAdapter = HotelsAdapter()
        tripBottomSheetBinding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        tripBottomSheetBinding.recyclerView.adapter = hotelsAdapter

        setTripModel()
        setDatePicker()
        getHotelsObserve()
    }

    private fun setDatePicker(){
        val myDataPicker = DataPicker(null, this)
            .setFormatType("dd.MM.yyyy")
            .setListener(object : OnDataSelectedListener{
                override fun isSelected(date: String) {
                    myDate = date
                    tripBottomSheetBinding.dateText.text = myDate
                }
            })
        tripBottomSheetBinding.dateButton.setOnClickListener {
            myDataPicker.showFragment()
        }
    }

    private fun setTripModel() {
        tripBottomSheetBinding.addBookmarkButton.setOnClickListener {
            val countryText = tripBottomSheetBinding.countryText.text.toString()
            val cityText = tripBottomSheetBinding.cityText.text.toString()
            if (cityText.isNotEmpty() || countryText.isNotEmpty()) {
                tripBookmarkViewModel.setTripModelLocalDB(TripModel(countryText, cityText, myDate, tripBottomSheetBinding.hotelsText.text.toString(), image))
            } else {
                Toast.makeText(context, "Please fill in the required fields!\n", Toast.LENGTH_SHORT).show()
            }
            val action = TripBottomSheetDirections.actionTripBottomSheetToTripScreenFragment()
            findNavController().navigate(action)
        }
    }

    private fun getHotelsObserve(){
            tripBookmarkViewModel.getBlogData.observe(viewLifecycleOwner){
                hotelsAdapter.setHotelsData(it)
            }

        hotelsAdapter.setOnClick(object: OnClick {
            override fun onClickItem(travelItem: TravelModelItem) {
                image = travelItem.images[0].url
                tripBottomSheetBinding.hotelsText.text = travelItem.title
                tripBottomSheetBinding.cityText.text = travelItem.city
                tripBottomSheetBinding.countryText.text = travelItem.country
            }
        })
    }

}
