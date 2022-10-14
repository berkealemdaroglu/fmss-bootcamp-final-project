package com.ersinberkealemdaroglu.tripplanapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.data.remote.localrepository.DatabaseRepository
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentDetailBinding
    private val navArgs: DetailFragmentArgs by navArgs()
    private val detailFragmentViewModel: DetailFragmentViewModel by viewModels()

    @Inject
    lateinit var databaseRepository: DatabaseRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        detailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBlogDataByArgs()
        bookmarkAddLocalDB()
        imageFull()
        bottomNavigationInvisible()
    }

    private fun setBlogDataByArgs() {
        navArgs.let {
            detailBinding.blogDataItem = it.blogData
        }
    }

    private fun bookmarkAddLocalDB() {
        detailBinding.addBookmarkButton.setOnClickListener {
            navArgs.let {
                detailFragmentViewModel.addBookmarkLocalDB(navArgs.blogData)
                Toast.makeText(context, "Added to Bookmark", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun imageFull() {
        detailBinding.imageButton.setOnClickListener {
            navArgs.let {
                findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragmentToImageFullSize(
                        navArgs.blogData
                    )
                )
            }
        }
    }

    private fun bottomNavigationInvisible() {
        //Bottom Navigation INVISIBLE
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navbarBlur =
            requireActivity().findViewById<ImageView>(R.id.background_blur_bottom_navigation)
        navBar.visibility = View.INVISIBLE
        navbarBlur.visibility = View.INVISIBLE
    }

}