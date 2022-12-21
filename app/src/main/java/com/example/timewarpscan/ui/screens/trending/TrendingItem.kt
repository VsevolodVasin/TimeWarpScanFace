package com.example.timewarpscan.ui.screens.trending

data class TrendingItem(
    val id: Int,
//    val previewUrl: String,
//    val videoUrl: String,
    val previewResId: Int,
    val videoUriPath: String,
    val title: String,
    val subTitle: String
)