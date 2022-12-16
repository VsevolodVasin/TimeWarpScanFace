package com.example.timewarpscan.ui.trending

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

//        (requireActivity() as NavigationHelper).showBottomAppBar()

        val trendingList: MutableList<TrendingItem> = ArrayList()
        trendingList.add(
            TrendingItem(
                0,
                "https://i.pinimg.com/564x/7e/88/1d/7e881d202ea407a2823be6a6231fa04b.jpg",
                "",
                "",
                ""
            )
        )

        trendingList.add(
            TrendingItem(
                1,
                "https://i.pinimg.com/564x/d8/22/f8/d822f88599eee4e2a33d606c26671623.jpg",
                "",
                "",
                ""
            )
        )

        trendingList.add(
            TrendingItem(
                2,
                "https://i.pinimg.com/564x/2f/c8/7f/2fc87f6520c822fc50a4ee187b6996d7.jpg",
                "",
                "",
                ""
            )
        )

        trendingList.add(
            TrendingItem(
                3,
                "https://i.pinimg.com/564x/79/c6/70/79c6706b707f74eaf24cef3a97415bc6.jpg",
                "",
                "",
                ""
            )
        )

        trendingList.add(
            TrendingItem(
                4,
                "https://i.pinimg.com/564x/ca/40/a6/ca40a6666c316c90890ea1326e7eeb87.jpg",
                "",
                "",
                ""
            )
        )

        trendingList.add(
            TrendingItem(
                5,
                "https://i.pinimg.com/564x/72/8a/54/728a548152b714304e206b3fa99806c8.jpg",
                "",
                "",
                ""
            )
        )

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