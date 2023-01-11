package com.example.timewarpscan.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timewarpscan.model.models.BlockedVideos
import com.example.timewarpscan.model.retrofit.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlockedVideosViewModel: ViewModel() {
    val blockedVideos = MutableLiveData<MutableList<Int>>()
    private val mServieces = Common.retrofitServieces

    fun fetchBlockedVideosFromApi() {
        mServieces.getBlockedVideos().enqueue(object : Callback<BlockedVideos> {
            override fun onResponse(call: Call<BlockedVideos>, response: Response<BlockedVideos>) {
                blockedVideos.postValue((response.body() as BlockedVideos).data as MutableList<Int>)
            }

            override fun onFailure(call: Call<BlockedVideos>, t: Throwable) {
                blockedVideos.postValue(mutableListOf())
                Log.e("BlockedVideosViewModel", "onFailure: ", t)
            }

        })
    }
}