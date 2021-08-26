package com.enofeb.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
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
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .weight(4f)
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    SellTextField()
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 15.dp)
                ) {
                    CurrencyDropDown(viewModel)
                }
            }
            Row(Modifier.padding(top = 15.dp)) {
                Column(
                    modifier = Modifier
                        .weight(4f)
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    BuyTextField()
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 15.dp)
                ) {
                    CurrencyDropDown(viewModel)
                }
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
fun CurrencyDropDown(viewModel: PriceViewModel) {

    val state = viewModel.priceUiState.collectAsState().value as? PriceUiState.FetchExchanges

    val currencies = state?.exchanges?.rates?.exchangeList?.map { it.unit }

    var expanded by remember { mutableStateOf(false) }

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
            currencies?.forEach { label ->
                label?.let {
                    DropdownMenuItem(onClick = { selectedText = it }) {
                        Text(text = it)
                    }
                }
            }
        }
    }

}

@Composable
fun CurrencyDropDownItem(currencies: List<String>) {

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