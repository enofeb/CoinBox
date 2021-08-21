package com.enofeb.price

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.math.exp

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
            Row {
                SellTextField()
                CurrencyDropDown()
            }
            Row{
                BuyTextField()
                CurrencyDropDown()
            }
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

@Composable
fun CurrencyDropDown() {
    var expanded by remember { mutableStateOf(false) }

    val currencies = listOf("BTC", "ETH", "SOL")

    var selectedText by remember { mutableStateOf("") }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            label = { Text("Name") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            currencies.forEach { label ->
                DropdownMenuItem(onClick = { selectedText = label }) {
                    Text(text = label)
                }
            }
        }
    }

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