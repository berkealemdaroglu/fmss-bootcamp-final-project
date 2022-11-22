package com.ersinberkealemdaroglu.tripplanapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ersinberkealemdaroglu.tripplanapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var bottomMenuNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

/*        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        actionBar?.hide()
        bottomNavigationMenu()

        FirebaseApp.initializeApp(/*context=*/this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance()
        )

        val analytics = FirebaseAnalytics.getInstance(this)
        with(Bundle()) {
            putString("Splash", "test")
            analytics.logEvent("Splash", this)
        }
    }

    private fun bottomNavigationMenu() {
        val navigationView =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomMenuNavigationView = findViewById(R.id.bottomNavigationView)
        bottomMenuNavigationView.setupWithNavController(navigationView.navController)
        bottomMenuNavigationView.itemIconTintList = null
    }
}