package com.example.timewarpscan.ui.screens.make_photo_and_video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timewarpscan.core.helpers.NavigationHelper
import com.example.timewarpscan.databinding.FragmentMakePhotoAndVideoBinding

class MakePhotoAndVideoFragment : Fragment() {

    lateinit var binding: FragmentMakePhotoAndVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMakePhotoAndVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            Log.i("TAG", "onBackPressedDispatcher")
            goBack() }
    }

    private fun goBack() {
        (requireActivity() as NavigationHelper).showBottomAppBar()
        findNavController().popBackStack()
    }

}