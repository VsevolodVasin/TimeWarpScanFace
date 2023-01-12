package time.warp.scan.face.scanner.slider.game.blue.line.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import time.warp.scan.face.scanner.slider.game.blue.line.model.models.BlockedLevels
import time.warp.scan.face.scanner.slider.game.blue.line.model.retrofit.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlockedLevelsViewModel: ViewModel() {
    val blockedLevels = MutableLiveData<MutableList<Int>>()
    private val mServieces = Common.retrofitServieces

    fun fetchBlockedLevelsFromApi() {
        mServieces.getBlockedLevels().enqueue(object : Callback<BlockedLevels> {
            override fun onResponse(call: Call<BlockedLevels>, response: Response<BlockedLevels>) {
                blockedLevels.postValue((response.body() as BlockedLevels).data as MutableList<Int>)
            }

            override fun onFailure(call: Call<BlockedLevels>, t: Throwable) {
                blockedLevels.postValue(mutableListOf())
                Log.e("BlockedLevelsViewModel", "onFailure: ", t)
            }

        })
    }
}