package com.ersinberkealemdaroglu.tripplanapp

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ersinberkealemdaroglu.tripplanapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var bottomMenuNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bottomNavigationMenu()
    }

    private fun bottomNavigationMenu(){
        val navigationView = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomMenuNavigationView = findViewById(R.id.bottomNavigationView)
        bottomMenuNavigationView.setupWithNavController(navigationView.navController)
        bottomMenuNavigationView.itemIconTintList = null
    }
}