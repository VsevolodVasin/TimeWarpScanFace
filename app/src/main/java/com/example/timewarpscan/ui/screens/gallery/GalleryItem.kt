package com.example.timewarpscan.ui.screens.gallery

data class GalleryItem(
    val id: Int,
    val type: GalleryItemType, // image, video
    val uri: String,
)
