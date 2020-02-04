package com.example.whenweather

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

        Timer("SettingUp", false).schedule(1500) {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        // If Location Permissions is already granted, go to next activity
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        } else {
            startActivity(Intent(this, LocationsActivity::class.java))

            // TODO: Implement Fade in and fade out transitions
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
