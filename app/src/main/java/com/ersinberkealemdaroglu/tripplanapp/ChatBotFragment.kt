package com.ersinberkealemdaroglu.tripplanapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.ersinberkealemdaroglu.tripplanapp.databinding.FragmentChatBotBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatBotFragment : Fragment() {
    private lateinit var chatBotBinding: FragmentChatBotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chatBotBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_bot, container, false)
        return chatBotBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatBotSetup()
        bottomNavigationInvisible()
    }

    private fun chatBotSetup(){
        val text = chatBotBinding.ghostView.setTextMessage()
    }

    private fun bottomNavigationInvisible(){
        //Bottom Navigation INVISIBLE
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navbarBlur = requireActivity().findViewById<ImageView>(R.id.background_blur_bottom_navigation)
        navBar.visibility = View.INVISIBLE
        navbarBlur.visibility = View.INVISIBLE
    }

}