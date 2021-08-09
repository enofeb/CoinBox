package com.enofeb.price

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.enofeb.core.data.price.Coin

@AndroidEntryPoint
class PriceFragment :
    BaseFragment<PriceIntent, PriceUiState, PriceViewModel>(PriceViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        triggerIntent(PriceIntent.FetchCoinsIntent)
    }

    @Composable
    override fun Render(state: PriceUiState) {
        when (state) {
            is PriceUiState.ShowCoins -> {
                CoinList(state.coins)
            }
            else -> {
                //no-op
            }
        }
    }
}

@Composable
fun CoinList(coins: List<Coin>?) {
    coins?.let { list ->
        LazyColumn {
            items(
                items = list,
                itemContent = { CoinItem(coin = it) })
        }

    }
}

@Composable
fun CoinItem(coin: Coin) {
    Row {
        Text(text = coin.name)
    }
}