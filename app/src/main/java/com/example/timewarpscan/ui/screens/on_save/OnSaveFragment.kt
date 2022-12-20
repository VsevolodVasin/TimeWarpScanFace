package com.example.timewarpscan.ui.screens.on_save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.timewarpscan.databinding.FragmentOnSaveBinding

class OnSaveFragment : Fragment() {

    lateinit var binding: FragmentOnSaveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnSaveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}