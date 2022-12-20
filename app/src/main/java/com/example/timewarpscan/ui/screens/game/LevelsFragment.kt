package com.example.timewarpscan.ui.screens.game

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timewarpscan.R
import com.example.timewarpscan.core.helpers.NavigationHelper
import com.example.timewarpscan.databinding.FragmentLevelsBinding

class LevelsFragment : Fragment() {

    lateinit var binding: FragmentLevelsBinding
    private val ARG_LEVEL_ID = "levelId"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLevelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) { goBack() }
        binding.apply {
            level2Button.isEnabled = true // TODO this is for test, remove after
            level3Button.isEnabled = true // TODO this is for test, remove after
            level4Button.isEnabled = true // TODO this is for test, remove after
            goBackButton.setOnClickListener { goBack() }
            levelsScrollView.post { levelsScrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            level1Button.setOnClickListener { levelClick(1) }
            level2Button.setOnClickListener { levelClick(2) }
            level3Button.setOnClickListener { levelClick(3) }
            level4Button.setOnClickListener { levelClick(4) }
            level5Button.setOnClickListener { levelClick(5) }
            level6Button.setOnClickListener { levelClick(6) }
            level7Button.setOnClickListener { levelClick(7) }
            level8Button.setOnClickListener { levelClick(8) }
            level9Button.setOnClickListener { levelClick(9) }
            level10Button.setOnClickListener { levelClick(10) }
        }
    }

    private fun levelClick(levelId: Int) {
        val selectedLevel = Levels(requireContext()).getLevelById(levelId)
        val dialog = Dialog(requireContext(), R.style.DialogStyle)
        dialog.apply {
            setContentView(R.layout.start_level_dialog)
            window?.setBackgroundDrawableResource(R.drawable.start_level_dialog_bg_inset)
            findViewById<TextView>(R.id.popupLevelNumber).text = "Level $levelId"
            findViewById<TextView>(R.id.popupLevelTitle).text = selectedLevel.title
            findViewById<TextView>(R.id.popupLevelCaption).text = selectedLevel.caption
            findViewById<ImageView>(R.id.popupLevelImage).setImageResource(selectedLevel.pictureId)
            findViewById<ImageView>(R.id.popupCloseButton).setOnClickListener { dismiss() }
            findViewById<Button>(R.id.playLevelButton).setOnClickListener {
                val bundle = Bundle()
                bundle.putInt(ARG_LEVEL_ID, levelId)
                dismiss()
                findNavController().navigate(R.id.action_levelsFragment_to_levelFragment, bundle)
            }
            show()
        }
    }

    private fun goBack() {
        findNavController().popBackStack()
        (requireActivity() as NavigationHelper).showBottomAppBar()
    }

}