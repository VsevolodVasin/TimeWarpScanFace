package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.trending

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import time.warp.scan.face.scanner.slider.game.blue.line.R
import time.warp.scan.face.scanner.slider.game.blue.line.core.helpers.NavigationHelper
import time.warp.scan.face.scanner.slider.game.blue.line.core.helpers.SpaceItemDecoration
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.FragmentTrendingBinding
import time.warp.scan.face.scanner.slider.game.blue.line.viewmodel.BlockedVideosViewModel


class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private val spanCount = 2
    lateinit var trendingItemsList: MutableList<TrendingItem>
    private lateinit var blockedVideosViewModel: BlockedVideosViewModel
    private var blockedItems: MutableList<Int> = mutableListOf()

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
        blockedVideosViewModel = ViewModelProvider(this)[BlockedVideosViewModel::class.java]
        blockedVideosViewModel.blockedVideos.observe(viewLifecycleOwner) {

            blockedItems = it
            getTrendingList()

            val trendingLayoutManager = GridLayoutManager(activity, spanCount)
            val trendingAdapter = TrendingAdapter(trendingItemsList, trendingItemListener)
            val countInLastLine = if (trendingItemsList.size % 2 == 1) 1 else 2
            val spaceItemDecoration: SpaceItemDecoration = if (countInLastLine == 1) {
                SpaceItemDecoration(listOf(trendingItemsList.size - 1))
            } else {
                SpaceItemDecoration(listOf(trendingItemsList.size - 1, trendingItemsList.size - 2))
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
        blockedVideosViewModel.fetchBlockedVideosFromApi()
    }

    private val trendingItemListener = TrendingAdapter.OnClickListener {
        Log.i("TAG", "You click on $it")
    }

    private fun getTrendingList() {

        val trendingList = mutableListOf<TrendingItem>()

        val previewDrawableIds = listOf(
            R.drawable.preview_1,
            R.drawable.preview_2,
            R.drawable.preview_3,
            R.drawable.preview_4,
            R.drawable.preview_5,
            R.drawable.preview_6,
            R.drawable.preview_7,
            R.drawable.preview_8,
            R.drawable.preview_9,
            R.drawable.preview_10,
            R.drawable.preview_11,
            R.drawable.preview_12,
            R.drawable.preview_13,
            R.drawable.preview_14,
            R.drawable.preview_15,
            R.drawable.preview_16,
            R.drawable.preview_17,
            R.drawable.preview_18,
            R.drawable.preview_19,
            R.drawable.preview_20,
            R.drawable.preview_21
        )

        val videoIds = listOf(
            R.raw.video_1,
            R.raw.video_2,
            R.raw.video_3,
            R.raw.video_4,
            R.raw.video_5,
            R.raw.video_6,
            R.raw.video_7,
            R.raw.video_8,
            R.raw.video_9,
            R.raw.video_10,
            R.raw.video_11,
            R.raw.video_12,
            R.raw.video_13,
            R.raw.video_14,
            R.raw.video_15,
            R.raw.video_16,
            R.raw.video_17,
            R.raw.video_18,
            R.raw.video_19,
            R.raw.video_20,
            R.raw.video_21
        )

        for (i in 0..20) {
            if (i+1 in blockedItems) continue
            trendingList.add(
                TrendingItem(
                    i,
                    previewDrawableIds[i],
                    "android.resource://" + context?.packageName + "/" + videoIds[i],
                    "",
                    ""
                )
            )
        }

        trendingItemsList = trendingList
    }

}