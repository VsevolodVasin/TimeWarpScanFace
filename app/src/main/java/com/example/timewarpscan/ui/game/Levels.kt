package com.example.timewarpscan.ui.game

import com.example.timewarpscan.R

class Levels {
    var levels: MutableList<Level> = arrayListOf(
        Level(1, "A long forehead", "Move yore face down to make a long forehead", R.drawable.forehead),
        Level(2, "Cut your finger", "Move your hand out to cut the finger", R.drawable.forehead),
        Level(3, "Doctor strange", "Move hands to create 6 hands like doctor strange", R.drawable.forehead),
        Level(4, "Hold your pencil", "Hold the pen in the air without using your hands", R.drawable.forehead),
        Level(5, "A long forehead", "Move yore face down to make a long forehead", R.drawable.forehead),
        Level(6, "A long forehead", "Move yore face down to make a long forehead", R.drawable.forehead),
        Level(7, "A long forehead", "Move yore face down to make a long forehead", R.drawable.forehead),
        Level(8, "A long forehead", "Move yore face down to make a long forehead", R.drawable.forehead),
        Level(9, "A long forehead", "Move yore face down to make a long forehead", R.drawable.forehead),
        Level(10, "A long forehead", "Move yore face down to make a long forehead", R.drawable.forehead)
    )

    fun getLevelById(levelId: Int): Level = levels[levelId - 1]

}