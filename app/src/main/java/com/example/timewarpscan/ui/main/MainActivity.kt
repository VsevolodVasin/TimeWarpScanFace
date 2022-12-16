package com.example.timewarpscan.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.timewarpscan.R
import com.example.timewarpscan.core.helpers.NavigationHelper
import com.example.timewarpscan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationHelper {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = this.findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        binding.fab.setOnClickListener {
            Log.i("TAG", "onCreate: FAB CLICK")
            hideBottomAppBar()
//            navController.navigate(R.id.action_navigation_trending_to_makePhotoAndVideoFragment)
//            startActivity(Intent(this, MakePhotoAndVideoActivity::class.java))
            navController.navigate(R.id.makePhotoAndVideoFragment)
        }
    }

    override fun showBottomAppBar() {
        binding.apply {
            fab.visibility = View.VISIBLE
            bottomAppBar.visibility = View.VISIBLE
            Log.i("TAG", "showBottomAppBar: ")
        }
    }

    override fun hideBottomAppBar() {
        binding.apply {
            bottomAppBar.visibility = View.GONE
            fab.visibility = View.GONE
            Log.i("TAG", "hideBottomAppBar: ")
        }
    }
}