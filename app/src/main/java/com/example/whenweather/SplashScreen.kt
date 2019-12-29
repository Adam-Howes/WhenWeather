package com.example.whenweather

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import java.util.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

        Thread.sleep(1000)

        newActivity()
    }

    fun newActivity(){
        startActivity(Intent(this@SplashScreen,MainScreen::class.java))
    }
}
