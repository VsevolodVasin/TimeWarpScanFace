package com.example.timewarpscan.ui.screens.game

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
class Level(
    val id: Int,
    val title: String,
    val caption: String,
    @DrawableRes val pictureId: Int,
) : Parcelable
