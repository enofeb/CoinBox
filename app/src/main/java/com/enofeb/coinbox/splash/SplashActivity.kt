package com.enofeb.coinbox.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.*

class SplashActivity : ComponentActivity() {

    private val splashScope = CoroutineScope(Dispatchers.Main) + SupervisorJob()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                InitialView()
                splashScope.launch {
                    delay(3000)
                    navigateToHome()
                }
            }
        }
    }

    override fun onPause() {
        splashScope.cancel()
        super.onPause()
    }

    private fun navigateToHome() {

        try {
            val intent = Intent(
                this,
                Class.forName(HOME_PATH)
            )
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val HOME_PATH = "com.enofeb.dashboard.HomeActivity"
    }
}

@Composable
fun InitialView() {
    Row {
        Text(text = "CoinBox")
    }
}

@Preview
@Composable
fun WatchPreview() {
    InitialView()
}