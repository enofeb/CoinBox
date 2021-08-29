package com.enofeb.price

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.enofeb.core.data.price.exchange.ExchangeRate
import kotlin.math.exp

@AndroidEntryPoint
class PriceFragment :
    BaseFragment<PriceIntent, PriceUiState>() {

    private val viewModel by viewModels<PriceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ComposeMagic {
                DrawScreen()
            }
        }
    }

    @Composable
    override fun DrawScreen() {
        PriceScreen(viewModel = viewModel)
    }
}

@Composable
fun PriceScreen(viewModel: PriceViewModel) {

    val state = viewModel.priceUiState.collectAsState().value

    val sellPrice = viewModel.sellPriceState.collectAsState().value

    val buyPrice = viewModel.buyPriceState.collectAsState().value

    var currencyList: List<ExchangeRate>? = remember { mutableListOf() }

    when (state) {
        is PriceUiState.FetchExchanges -> {
            currencyList = state.exchanges
        }
        is PriceUiState.LoadingState -> {
        }
        else -> {
        }
    }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        PriceHeaderField()
        Row(Modifier.padding(top = 15.dp)) {
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                SellTextField(onPriceChange = { viewModel.onSellPriceChange(it.toDoubleOrNull()) })
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 15.dp)
            ) {
                CurrencyDropDown(currencyList, isBitcoin = true)
            }
        }
        Row(Modifier.padding(top = 15.dp)) {
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                BuyTextField(buyPrice)
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 15.dp)
            ) {
                CurrencyDropDown(
                    currencyList,
                    onCurrencyChange = { viewModel.onBuyCurrency(it.toDoubleOrNull()) })
            }
        }
    }
}

@Composable
fun SellTextField(onPriceChange: (String) -> Unit) {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            onPriceChange.invoke(it)
        },
        label = { Text("Sell") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
    )
}

@Composable
fun BuyTextField(price: Double?) {

    OutlinedTextField(
        value = price.toString(),
        onValueChange = {},
        enabled = false,
        label = { Text("Buy") }
    )
}

@Composable
fun CurrencyDropDown(
    currencyList: List<ExchangeRate>?,
    isBitcoin: Boolean = false,
    onCurrencyChange: ((String) -> Unit)? = null
) {

    var expanded by remember { mutableStateOf(false) }

    var selectedText by remember { mutableStateOf("") }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column {
        OutlinedTextField(
            value = if (isBitcoin) currencyList?.firstOrNull()?.unit ?: "" else selectedText,
            onValueChange = { selectedText = it },
            label = { Text("Name") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = if (isBitcoin) false else expanded,
            onDismissRequest = { expanded = false }) {
            currencyList?.forEach { currency ->
                DropdownMenuItem(onClick = {
                    selectedText = currency.unit
                    onCurrencyChange?.invoke(currency.value.toString())
                }) {
                    Text(text = currency.unit)
                }
            }
        }
    }

}

@Composable
fun PriceHeaderField() {
    Row(Modifier.padding(start = 15.dp)) {
        Icon(painter = painterResource(id = R.drawable.ic_change), contentDescription = null)
        Text(
            text = "BTC Converter",
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun ItemPreview() {
    Row(horizontalArrangement = Arrangement.Start) {
        Icon(painter = painterResource(id = R.drawable.ic_change), contentDescription = null)
        Text(text = "BTC Converter")
    }
}