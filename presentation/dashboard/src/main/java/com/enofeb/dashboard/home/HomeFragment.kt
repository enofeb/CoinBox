package com.enofeb.dashboard.home

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enofeb.core.base.BaseFragment
import com.enofeb.core.data.price.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeIntent, HomeUiState, HomeViewModel>(HomeViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        triggerIntent(HomeIntent.FetchMarketIntent)
    }

    @Composable
    override fun Render(state: HomeUiState) {
        when (state) {
            is HomeUiState.ShowMarket -> {
                CoinList(state.coins)
            }
            is HomeUiState.LoadingState -> {
                //no-op
            }
            else -> {
                //no-op
            }
        }
    }

    @Composable
    override fun DrawScreen() {
        //no-op
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
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(15.dp), elevation = 10.dp,
        backgroundColor = Color.Black
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = coin.name, color = Color.White)
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    Row {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = "Bitcoin", color = Color.White)
        }
    }
}