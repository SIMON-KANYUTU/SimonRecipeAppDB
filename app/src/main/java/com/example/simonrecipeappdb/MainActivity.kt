package com.example.simonrecipeappdb

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : ComponentActivity() {

    private val splashScreen: Long = 3000 // 3 seconds delay for the splash screen.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Handler to delay navigating to the Home Activity Page.
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity to prevent the user from navigating back to it.
        }, splashScreen)
    }
}
