package com.example.timewarpscan.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timewarpscan.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

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
        }.start()
    }
}