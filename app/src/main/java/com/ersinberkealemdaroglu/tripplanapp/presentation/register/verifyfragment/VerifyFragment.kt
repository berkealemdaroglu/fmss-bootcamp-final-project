package com.ersinberkealemdaroglu.tripplanapp.presentation.register.verifyfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentVerifyBinding
import com.ersinberkealemdaroglu.tripplanapp.presentation.register.RegisterFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyFragment : Fragment() {

    private lateinit var verifyBinding: FragmentVerifyBinding
    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String = ""
    private val registerFragmentViewModel: RegisterFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        verifyBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_verify, container, false)
        return verifyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bottomNavigationVisible()


    }


    private fun bottomNavigationVisible() {
        //Bottom Navigation VISIBLE
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navbarBlur =
            requireActivity().findViewById<ImageView>(R.id.background_blur_bottom_navigation)
        navBar.visibility = View.INVISIBLE
        navbarBlur.visibility = View.INVISIBLE
    }

}