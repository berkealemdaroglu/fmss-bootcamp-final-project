package com.ersinberkealemdaroglu.tripplanapp.presentation.register

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentRegisterBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var signUpBinding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        signUpBinding = FragmentRegisterBinding.inflate(inflater)
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButtonClick()
        bottomNavigationVisible()
    }

    /**
     *  İlk etapta Edittexlerin boş olup olmadığı kontrol ediliyor.
     *  Daha önce girilen mail adresi ile kayıt var mı kontrol ediliyor.
     *  Herşey uygunsa Firabase database e kayıt yapılarak HomeFragment a yönlendiliyor.
     */
    private fun signUpButtonClick() {
        signUpBinding.signUpButton.setOnClickListener {
            val email = signUpBinding.editTextTextEmailAddress.text.toString()
            val password = signUpBinding.editTextTextPassword.text.toString()

            if (email == "" || password == "") {
                Toast.makeText(context, "Enter email and password!", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                }.addOnFailureListener {
                    if(password.length <= 6){
                        Toast.makeText(context, "Lütfen 6 Karakterden Uzun Şifre Girin!",
                            Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "This Email Address Already Exists",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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