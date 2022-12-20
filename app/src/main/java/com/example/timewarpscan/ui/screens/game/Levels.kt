package com.example.timewarpscan.ui.screens.game

import android.content.Context
import com.example.timewarpscan.R

class Levels(val context: Context) {
    private var levels: MutableList<Level> = arrayListOf(
        Level(1, "A long forehead", "Move yore face down to make a long forehead", R.drawable.preview_1, "android.resource://" + context.packageName + "/" + R.raw.video_1),
        Level(2, "Cut your finger", "Move your hand out to cut the finger", R.drawable.preview_2, "android.resource://" + context.packageName + "/" + R.raw.video_2),
        Level(3, "Doctor strange", "Move hands to create 6 hands like doctor strange", R.drawable.preview_3, "android.resource://" + context.packageName + "/" + R.raw.video_3),
        Level(4, "Hold your pencil", "Hold the pen in the air without using your hands", R.drawable.preview_4, "android.resource://" + context.packageName + "/" + R.raw.video_4),
        Level(5, "Push-up", "Push-up without hands", R.drawable.preview_5, "android.resource://" + context.packageName + "/" + R.raw.video_5),
        Level(6, "Kimetsu no yaiba", "Find a friend and transform into Kimetsu no yaiba", R.drawable.preview_6, "android.resource://" + context.packageName + "/" + R.raw.video_6),
        Level(7, "Twin", "Twin friend", R.drawable.preview_7, "android.resource://" + context.packageName + "/" + R.raw.video_7),
        Level(8, "Mirror", "Is there someone in the mirror?", R.drawable.preview_8, "android.resource://" + context.packageName + "/" + R.raw.video_8),
        Level(9, "Sagittarius", "Turn into a Sagittarius", R.drawable.preview_9, "android.resource://" + context.packageName + "/" + R.raw.video_9),
        Level(10, "Ok", "Can you make this?", R.drawable.preview_10, "android.resource://" + context.packageName + "/" + R.raw.video_10)
    )

    fun getLevelById(levelId: Int): Level = levels[levelId - 1]

}