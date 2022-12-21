package com.example.timewarpscan.ui.screens.trending

import android.content.Context
import com.example.timewarpscan.R
import com.example.timewarpscan.data.BlockedItems

class TrendingList {

    fun getTrendingList(context: Context): MutableList<TrendingItem> {
        val trendingList = mutableListOf<TrendingItem>()

        val blockedItems = BlockedItems().getBlockedItems()

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
                    "android.resource://" + context.packageName + "/" + videoIds[i],
                    "",
                    ""
                )
            )
        }

        return trendingList
    }

}