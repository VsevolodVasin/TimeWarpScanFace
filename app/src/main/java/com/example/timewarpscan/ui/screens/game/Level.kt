package com.example.timewarpscan.ui.screens.game

import androidx.annotation.DrawableRes

class Level(
    val id: Int,
    val title: String,
    val caption: String,
    @DrawableRes val pictureId: Int,
    val videoUriPath: String,
)
