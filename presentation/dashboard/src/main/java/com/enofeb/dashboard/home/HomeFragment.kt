package com.enofeb.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.enofeb.core.base.BaseFragment
import com.enofeb.core.data.market.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeIntent, HomeUiState>() {

    private val viewModel by viewModels<HomeViewModel>()

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
        CoinList(viewModel)
    }

}


@Composable
fun CoinList(viewModel: HomeViewModel) {
    when (val state = viewModel.homeUiState.collectAsState().value) {
        is HomeUiState.LoadingState -> {
            ShowProgress()
        }
        is HomeUiState.ShowMarket -> {
            state.coins?.let { list ->
                LazyColumn {
                    items(
                        items = list,
                        itemContent = { CoinItem(coin = it) })
                }
            }
        }
        is HomeUiState.InitialState -> {

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
        Row(modifier = Modifier.padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                Image(
                    painter = rememberImagePainter(coin.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.dp, 15.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = coin.name,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(text = coin.currentPrice.toString(), color = Color.White)
        }
    }
}

@Composable
fun ShowProgress() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MarketOrderTabs() {
    //Market order tabs will be here
}

@Preview
@Composable
fun ItemPreview() {
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(15.dp), elevation = 10.dp,
        backgroundColor = Color.Black
    ) {
        Row(modifier = Modifier.padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                Image(
                    painter = rememberImagePainter("https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880"),
                    contentDescription = null,
                    Modifier.size(10.dp, 10.dp)
                )
                Text(text = "Eth", color = Color.White)
            }
            Text(text = "3224.5", color = Color.White)
        }
    }
}