package com.example.timewarpscan.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timewarpscan.core.helpers.NavigationHelper
import com.example.timewarpscan.databinding.FragmentLevelsBinding

class LevelsFragment : Fragment() {

    lateinit var binding: FragmentLevelsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLevelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) { goBack() }
        binding.goBackButton.setOnClickListener { goBack() }
    }

    private fun goBack() {
        findNavController().popBackStack()
        (requireActivity() as NavigationHelper).showBottomAppBar()
    }

}