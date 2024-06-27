package com.s21.presentation

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.s21.ticketsapp.R
import com.s21.ticketsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tickets, R.id.navigation_otels, R.id.navigation_coroche,
                R.id.navigation_subs, R.id.navigation_profiles
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.id == R.id.choiseTicketFragment
                || destination.id == R.id.allTicketsFragment
            ) {
                navView.visibility = View.GONE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                navView.visibility = View.VISIBLE
            }
        }
    }

}