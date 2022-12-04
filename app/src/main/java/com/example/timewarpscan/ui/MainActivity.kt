package com.example.timewarpscan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.timewarpscan.R
import com.example.timewarpscan.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.trending -> navController.navigate(R.id.trendingFragment)
                R.id.explore -> navController.navigate(R.id.exploreFragment)
                R.id.game -> navController.navigate(R.id.gameFragment)
                R.id.gallery -> navController.navigate(R.id.galleryFragment)
                else -> Log.i(TAG, "onCreate: ths wrong")
            }
            return@setOnItemSelectedListener true
        }

        binding.fab.setOnClickListener { navController.navigate(R.id.makePhotoAndVideoFragment) }

    }
}