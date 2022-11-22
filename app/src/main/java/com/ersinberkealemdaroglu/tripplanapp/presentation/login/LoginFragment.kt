package com.ersinberkealemdaroglu.tripplanapp.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.ersinberkealemdaroglu.tripplanapp.R
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButtonOnClick()
        signUpButtonClick()
        bottomNavigationVisible()
    }

    /**
     * auth.signInWithEmailAndPassword ile Firebase de bulunan kullanıcıların email ve şifrelerini kontrol ediyor.
     * Eğer bilgiler uyuşuyorsa giriş yaparak homeFragment a yönlendiliyor.
     */
    private fun loginButtonOnClick() {
        loginBinding.loginButton.setOnClickListener {
            val emailText = loginBinding.editTextTextEmailAddress.text.toString()
            val passwordText = loginBinding.editTextTextPassword.text.toString()

            if (emailText == "" && passwordText == ""){
                Toast.makeText(context, "Lütfen Gerekli Alanları Doldurun!", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(emailText, passwordText).addOnSuccessListener {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }.addOnFailureListener {
                    Toast.makeText(context, "Girilen Bilgiler Yanlıştır!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Kullanıcıların kayıt yapabilesmi için signUpFragment a yönlendiliyor.
    private fun signUpButtonClick(){
        loginBinding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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