package com.example.timewarpscan.ui.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timewarpscan.R
import com.example.timewarpscan.databinding.FragmentLevelBinding


class LevelFragment : Fragment() {
    private val ARG_LEVEL_ID = "levelId"
    private val ARG_LEVEL_VIDEO_URI_PATH = "videoUriPath"
    private val ARG_IS_LEVEL_BLOCKED = "isLevelBlocked"
    private var isLevelBlocked: Boolean = false
    private var levelId: Int? = null
    lateinit var binding: FragmentLevelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            levelId = it.getInt(ARG_LEVEL_ID)
            isLevelBlocked = it.getBoolean(ARG_IS_LEVEL_BLOCKED)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLevelBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val level = Levels(requireContext()).getLevelById(levelId!!)
        binding.apply {
            if (isLevelBlocked) {
                helpButton.visibility = View.GONE
                goalImageView.visibility = View.GONE
            } else {
                goalImageView.setImageResource(level.pictureId)
                helpButton.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString(ARG_LEVEL_VIDEO_URI_PATH, level.videoUriPath)
                    findNavController().navigate(R.id.action_levelFragment_to_helpFragment, bundle)
                }
            }
            levelBackButton.setOnClickListener { findNavController().popBackStack() }
        }
    }
}