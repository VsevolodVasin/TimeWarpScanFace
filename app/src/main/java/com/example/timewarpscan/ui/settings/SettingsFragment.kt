package com.example.timewarpscan.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.timewarpscan.R
import com.example.timewarpscan.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        binding.backButton.setOnClickListener { goBack() }

    private fun goBack() {
        Navigation.findNavController(
            requireActivity(),
            R.id.activity_root__fragment__nav_host
        ).popBackStack()
    }

}