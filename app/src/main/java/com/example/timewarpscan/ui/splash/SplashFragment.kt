package com.example.timewarpscan.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.timewarpscan.R
import com.example.timewarpscan.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var progressBarStatus = 0

        Thread {
            while (progressBarStatus < 100) {
                try {
                    progressBarStatus += 1
                    Thread.sleep(20)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                binding.progressBar.progress = progressBarStatus
            }

            val navController = Navigation.findNavController(requireActivity(), R.id.activity_root__fragment__nav_host)
            val mainGraph = navController.navInflater.inflate(R.navigation.app_nav_graph)
            mainGraph.setStartDestination(R.id.mainFragment)

            requireActivity().runOnUiThread {
                navController.graph = mainGraph
            }

        }.start()
    }

}