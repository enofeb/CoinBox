package com.enofeb.coinbox.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enofeb.coinbox.R
import com.enofeb.coinbox.home.HomeActivity
import kotlinx.coroutines.*

class SplashActivity : ComponentActivity() {

    private val splashScope = CoroutineScope(Dispatchers.Main) + SupervisorJob()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                InitialView()
                splashScope.launch {
                    delay(5000)
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
                HomeActivity::class.java
            )
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }
}

@Composable
fun InitialView() {

    val infiniteTransition = rememberInfiniteTransition()
    val infiniteAnimatedFloat = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 180f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000), repeatMode = RepeatMode.Restart
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F8FF))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_z_cash),
            contentDescription = "Desc",
            modifier = Modifier
                .size(100.dp)
                .rotate(infiniteAnimatedFloat.value)
        )

    }
}

@Preview
@Composable
fun WatchPreview() {
    InitialView()
}