package com.example.timewarpscan.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timewarpscan.R
import com.example.timewarpscan.core.helpers.NavigationHelper
import com.example.timewarpscan.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.playGameButton.setOnClickListener {
            (requireActivity() as NavigationHelper).hideBottomAppBar()
            findNavController().navigate(R.id.action_navigation_game_to_levelsFragment)
        }
    }

}