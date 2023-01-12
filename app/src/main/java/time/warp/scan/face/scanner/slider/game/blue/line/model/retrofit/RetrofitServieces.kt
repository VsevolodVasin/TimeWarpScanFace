package time.warp.scan.face.scanner.slider.game.blue.line.model.retrofit

import time.warp.scan.face.scanner.slider.game.blue.line.model.models.BlockedLevels
import time.warp.scan.face.scanner.slider.game.blue.line.model.models.BlockedVideos
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServieces {
    @GET("levels")
    fun getBlockedLevels(): Call<BlockedLevels>

    @GET("videos")
    fun getBlockedVideos(): Call<BlockedVideos>
}