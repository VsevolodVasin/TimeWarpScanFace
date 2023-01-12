package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import time.warp.scan.face.scanner.slider.game.blue.line.R
import time.warp.scan.face.scanner.slider.game.blue.line.core.helpers.NavigationHelper
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.ActivityMainBinding
import time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.EffectActivity

class MainActivity : AppCompatActivity(), NavigationHelper {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

//    override fun onResume() {
//        showBottomAppBar()
//        super.onResume()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = this.findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        binding.fab.setOnClickListener {
            Log.i("TAG", "onCreate: FAB CLICK")
            hideBottomAppBar()
            val i = Intent(this, EffectActivity::class.java)
            startActivity(i)

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