package com.example.timewarpscan.ui.screens.game

import android.app.Dialog
import android.content.Context
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
import com.example.timewarpscan.data.BlockedItems
import com.example.timewarpscan.databinding.FragmentLevelsBinding

class LevelsFragment : Fragment() {

    lateinit var binding: FragmentLevelsBinding
    private val ARG_LEVEL_ID = "levelId"
    private val ARG_IS_LEVEL_BLOCKED = "isLevelBlocked"
    val APP_PREFERENCES = "settings"
    val APP_PREFERENCES_COMPLETED_LEVELS = "Completed level" // имя кота

    private lateinit var blockedItems: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLevelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

//        val editor = sharedPreferences.edit()
//        editor.putInt(APP_PREFERENCES_COMPLETED_LEVELS, 10)
//        editor.apply()

        val completedLevel = sharedPreferences.getInt(APP_PREFERENCES_COMPLETED_LEVELS, 1)

        blockedItems = BlockedItems().getBlockedItems()

        requireActivity().onBackPressedDispatcher.addCallback(this) { goBack() }
        binding.apply {
            val levelButtonsIds = listOf(
                level1Button,
                level2Button,
                level3Button,
                level4Button,
                level5Button,
                level6Button,
                level7Button,
                level8Button,
                level9Button,
                level10Button,
            )

            for (i in 1..completedLevel) {
                levelButtonsIds[i-1].isEnabled = true
                levelButtonsIds[i-1].setOnClickListener { levelClick(i) }
            }

            if (completedLevel == 10) crystalButton.setOnClickListener { crystalClick() }

            levelsScrollView.post { levelsScrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            goBackButton.setOnClickListener { goBack() }
            playLastLevelButton.setOnClickListener { levelClick(completedLevel) }
        }
    }

    private fun crystalClick() {
        val dialog = Dialog(requireContext(), R.style.DialogStyle)
        dialog.apply {
            setContentView(R.layout.finish_game_dialog)
            window?.setBackgroundDrawableResource(R.drawable.start_level_dialog_bg_inset)
            findViewById<Button>(R.id.finishPopup_playButton).setOnClickListener {
                dismiss()
                binding.levelsScrollView.post {
                    binding.levelsScrollView.fullScroll(ScrollView.FOCUS_DOWN)
                    levelClick(1)
                }
            }
            findViewById<ImageView>(R.id.finishPopupCloseButton).setOnClickListener { dismiss() }
            show()
        }
    }

    private fun levelClick(levelId: Int) {
        val selectedLevel = Levels(requireContext()).getLevelById(levelId)
        val dialog = Dialog(requireContext(), R.style.DialogStyle)
        val isLevelBlocked = levelId in blockedItems
        dialog.apply {
            setContentView(R.layout.start_level_dialog)
            window?.setBackgroundDrawableResource(R.drawable.start_level_dialog_bg_inset)
            findViewById<TextView>(R.id.popupLevelNumber).text = "Level $levelId"
            findViewById<TextView>(R.id.popupLevelTitle).text = selectedLevel.title
            findViewById<TextView>(R.id.popupLevelCaption).text = selectedLevel.caption
            if (isLevelBlocked) {
                findViewById<ImageView>(R.id.popupLevelImage).visibility = View.GONE
            } else {
                findViewById<ImageView>(R.id.popupLevelImage).setImageResource(selectedLevel.pictureId)
            }
            findViewById<ImageView>(R.id.popupCloseButton).setOnClickListener { dismiss() }
            findViewById<Button>(R.id.playLevelButton).setOnClickListener {
                val bundle = Bundle()
                bundle.putInt(ARG_LEVEL_ID, levelId)
                bundle.putBoolean(ARG_IS_LEVEL_BLOCKED, isLevelBlocked)
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