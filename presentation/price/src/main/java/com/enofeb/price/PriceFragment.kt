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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.enofeb.core.data.price.exchange.ExchangeRate
import com.enofeb.core.ui.dimens.DefaultPadding
import com.enofeb.core.ui.dimens.SmallPadding

@AndroidEntryPoint
class PriceFragment : BaseFragment() {

    private val viewModel by viewModels<PriceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ComposeMagic {
                PriceScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun PriceScreen(viewModel: PriceViewModel) {

    val state = viewModel.priceUiState.collectAsState().value

    val buyPrice = viewModel.buyPriceState.collectAsState().value

    ConverterContent(
        onPriceChange = { viewModel.onSellPriceChange(it.toDoubleOrNull()) },
        currencyList = state.exchanges,
        buyPrice = buyPrice,
        onCurrencyChange = { viewModel.onBuyCurrency(it.toDoubleOrNull()) }
    )

}

@Composable
fun ConverterContent(
    onPriceChange: (String) -> Unit,
    currencyList: List<ExchangeRate>?,
    buyPrice: Double?,
    onCurrencyChange: ((String) -> Unit)? = null
) {

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        PriceHeaderField(icon = R.drawable.ic_change, "BTC Converter")
        Row(Modifier.padding(top = DefaultPadding)) {
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = DefaultPadding, end = DefaultPadding)
            ) {
                SellTextField(onPriceChange = onPriceChange)
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = DefaultPadding)
            ) {
                CurrencyDropDown(currencyList, isBitcoin = true)
            }
        }
        Row(Modifier.padding(top = DefaultPadding)) {
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = DefaultPadding, end = DefaultPadding)
            ) {
                BuyTextField(buyPrice)
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = DefaultPadding)
            ) {
                CurrencyDropDown(
                    currencyList,
                    onCurrencyChange = onCurrencyChange
                )
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
fun PriceHeaderField(icon: Int, text: String) {
    Row(Modifier.padding(start = DefaultPadding)) {
        Icon(painter = painterResource(id = icon), contentDescription = null)
        Text(
            text = text,
            modifier = Modifier
                .padding(start = SmallPadding)
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