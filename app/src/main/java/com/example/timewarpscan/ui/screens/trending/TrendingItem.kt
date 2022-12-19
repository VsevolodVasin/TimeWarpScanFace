package com.example.timewarpscan.ui.screens.trending

import androidx.annotation.DrawableRes

data class TrendingItem(
    val id: Int,
//    val previewUrl: String,
//    val videoUrl: String,
    @DrawableRes val previewResId: Int,
    val videoUriPath: String,
    val title: String,
    val subTitle: String
)