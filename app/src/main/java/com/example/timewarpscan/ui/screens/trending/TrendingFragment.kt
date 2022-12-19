package com.example.timewarpscan.ui.screens.trending

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.timewarpscan.R
import com.example.timewarpscan.core.helpers.NavigationHelper
import com.example.timewarpscan.core.helpers.SpaceItemDecoration
import com.example.timewarpscan.databinding.FragmentTrendingBinding


class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private val spanCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val dialog = Dialog(requireContext(), R.style.DialogStyle)
            dialog.apply {
                setContentView(R.layout.custom_dialog)
                window?.setBackgroundDrawableResource(R.drawable.dialog_background_inset)
                findViewById<Button>(R.id.dismissDialogButton).setOnClickListener { dismiss() }
                findViewById<Button>(R.id.agreeDialogButton).setOnClickListener {
                    dismiss()
                    requireActivity().finish()
                }
                show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val trendingList = TrendingList().getTrendingList(requireContext())

        val trendingLayoutManager = GridLayoutManager(activity, spanCount)
        val trendingAdapter = TrendingAdapter(trendingList, trendingItemListener)
        val countInLastLine = if (trendingList.size % 2 == 1) 1 else 2
        val spaceItemDecoration: SpaceItemDecoration = if (countInLastLine == 1) {
            SpaceItemDecoration(listOf(trendingList.size - 1))
        } else {
            SpaceItemDecoration(listOf(trendingList.size - 1, trendingList.size - 2))
        }

        binding.apply {
            trendingRecyclerView.addItemDecoration(spaceItemDecoration)
            trendingRecyclerView.layoutManager = trendingLayoutManager
            trendingRecyclerView.adapter = trendingAdapter
            settingsButton.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_trending_to_settingsFragment)
                (requireActivity() as NavigationHelper).hideBottomAppBar()
            }
        }
    }

    private val trendingItemListener = TrendingAdapter.OnClickListener {
        Log.i("TAG", "You click on $it")
    }

}