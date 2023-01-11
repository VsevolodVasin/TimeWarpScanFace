package com.example.timewarpscan.model.retrofit

import com.example.timewarpscan.model.models.BlockedLevels
import com.example.timewarpscan.model.models.BlockedVideos
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServieces {
    @GET("levels")
    fun getBlockedLevels(): Call<BlockedLevels>

    @GET("videos")
    fun getBlockedVideos(): Call<BlockedVideos>
}