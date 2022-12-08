package com.example.timewarpscan.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.timewarpscan.R
import com.example.timewarpscan.databinding.ActivityRootBinding
import com.example.timewarpscan.helpers.NavHelper
import com.example.timewarpscan.helpers.UiHelper

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding
    private lateinit var navController: NavController
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        binding.bottomNavigationView.background = null
//        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
//
//        binding.bottomNavigationView.selectedItemId = R.id.gallery

//        binding.fab.setOnClickListener { navController.navigate(R.id.makePhotoAndVideoFragment) }
    }


//    override fun navigate(fragmentId: Int) {
//        navController.navigate(fragmentId)
//    }

//    override fun back(selectedItemId: Int?) {
//        if (selectedItemId != null) {
//            binding.bottomNavigationView.selectedItemId = selectedItemId
//        }
//        navController.popBackStack()
//    }

//    override fun hideBottomAppBar() {
//        binding.apply {
//            bottomAppBar.visibility = View.GONE
//            fab.visibility = View.GONE
//        }
//    }
//
//    override fun showBottomAppBar() {
//        binding.apply {
//            bottomAppBar.visibility = View.VISIBLE
//            fab.visibility = View.VISIBLE
//        }
//    }
}