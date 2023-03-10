package time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import time.warp.scan.face.scanner.slider.game.blue.line.databinding.ActivitySplashBinding
import time.warp.scan.face.scanner.slider.game.blue.line.ui.screens.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var progressBarStatus = 0

        Thread {
            while (progressBarStatus < 100) {
                try {
                    progressBarStatus += 1
                    Thread.sleep(20)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                binding.progressBar.progress = progressBarStatus
            }

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }.start()
    }

}