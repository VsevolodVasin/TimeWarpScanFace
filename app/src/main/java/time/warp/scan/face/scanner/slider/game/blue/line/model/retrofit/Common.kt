package time.warp.scan.face.scanner.slider.game.blue.line.model.retrofit

object Common {
    private const val BASE_URL = "http://46.254.16.112:8080/api/"
    val retrofitServieces: RetrofitServieces
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServieces::class.java)
}