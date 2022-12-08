package com.example.timewarpscan.helpers

interface NavHelper {
    fun navigate(fragmentId: Int)
    fun back(selectedItemId: Int? = null)
}