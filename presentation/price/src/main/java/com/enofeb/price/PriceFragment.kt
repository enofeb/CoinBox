package com.enofeb.price

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.TextField
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

@AndroidEntryPoint
class PriceFragment :
    BaseFragment<PriceIntent, PriceUiState, PriceViewModel>(PriceViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        triggerIntent(PriceIntent.FetchExchangesIntent)
    }

    @Composable
    override fun Render(state: PriceUiState) {
        when (state) {
            is PriceUiState.FetchExchanges -> {
            }
            is PriceUiState.LoadingState -> {
                //no-op
            }
            else -> {
                //no-op
            }
        }
    }

    @Composable
    override fun DrawScreen() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SellTextField()
            BuyTextField()
        }
    }
}

@Composable
fun SellTextField() {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Sell") }
    )
}

@Composable
fun BuyTextField() {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Buy") }
    )
}

@Preview
@Composable
fun ItemPreview() {
    var value by remember { mutableStateOf("") }

    Row {
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Sell") }
        )
    }
}