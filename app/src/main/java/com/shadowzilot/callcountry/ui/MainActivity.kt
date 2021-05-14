package com.shadowzilot.callcountry.ui

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shadowzilot.callcountry.R
import com.shadowzilot.callcountry.database.CountryLab

class MainActivity : AppCompatActivity() {
    lateinit var mBottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode())
        setContentView(R.layout.activity_main)
        CountryLab.get(this).getLogList()
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragment_host) as NavHostFragment? ?: return

        val navController = host.navController

        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        mBottomNav = findViewById(R.id.bottom_nav)
        mBottomNav.setupWithNavController(navController)
    }
}