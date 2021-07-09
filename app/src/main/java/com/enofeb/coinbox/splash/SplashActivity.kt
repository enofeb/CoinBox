package com.enofeb.coinbox.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enofeb.coinbox.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//Will be done with Compose
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        runBlocking {
            delay(100)
        }
    }
}