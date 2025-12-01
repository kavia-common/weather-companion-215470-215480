package org.example.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * PUBLIC_INTERFACE
 * MainActivity hosts the app's navigation using a BottomNavigationView with five primary destinations:
 * Dashboard, Forecast, Search, Favorites, and Map.
 *
 * Implementation detail:
 * - We obtain the NavController via the NavHostFragment to avoid timing issues that can occur
 *   on some devices when calling findNavController() directly at Activity startup.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Retrieve the NavController from the NavHostFragment instance
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Wire BottomNavigationView with the NavController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)
    }
}
