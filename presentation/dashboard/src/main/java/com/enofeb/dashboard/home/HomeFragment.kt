package com.enofeb.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.enofeb.core.base.BaseFragment
import com.enofeb.core.data.market.Coin
import com.enofeb.core.data.market.order.HomeOrderType
import com.enofeb.core.extensions.roundOffDecimal
import com.enofeb.dashboard.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.pager.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel by viewModels<HomeViewModel>()

    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ComposeMagic {
                CoinScreen(viewModel)
            }
        }
    }

}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CoinScreen(viewModel: HomeViewModel) {

    val state = viewModel.homeUiState.collectAsState().value

    val errorState = viewModel.errorState.collectAsState().value

    val loadingState = viewModel.loadingState.collectAsState().value

    val listOrderTypes: List<HomeOrderType> = listOf(
        HomeOrderType.HOT,
        HomeOrderType.GAINER,
        HomeOrderType.LOSERS,
        HomeOrderType.TODAYHIGH
    )

    val pagerState = rememberPagerState(pageCount = listOrderTypes.size)

    if (loadingState.isLoading == true) {
        ShowProgress()
    }

    Scaffold(topBar = { HomeAppBar() }) {
        Column {
            MarketOrderTabs(listOrderTypes, pagerState)
            MarketOrderTabsContent(
                pagerState,
                state.hotCoins,
                state.gainCoins,
                state.loserCoins,
                state.todayHighCoins
            )
        }
    }

}

@Composable
fun CoinList(coins: List<Coin>?) {
    coins?.let { list ->
        Column(Modifier.fillMaxSize()) {
            LazyColumn {
                items(
                    items = list,
                    itemContent = { CoinItem(coin = it) })
            }
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

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MarketOrderTabs(pages: List<HomeOrderType>, pagerState: PagerState) {

    TabRow(selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        pages.forEachIndexed { index, item ->
            Tab(
                text = { Text(item.title) },
                selected = pagerState.currentPage == index,
                onClick = {}
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MarketOrderTabsContent(
    pagerState: PagerState,
    hotCoins: List<Coin>?,
    gainerCoins: List<Coin>?,
    loserCoins: List<Coin>?,
    todayHighCoins: List<Coin>?,
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            HomeOrderType.HOT.ordinal -> {
                CoinList(hotCoins)
            }
            HomeOrderType.GAINER.ordinal -> {
                CoinList(gainerCoins)
            }
            HomeOrderType.LOSERS.ordinal -> {
                CoinList(loserCoins)
            }
            HomeOrderType.TODAYHIGH.ordinal -> {
                CoinList(todayHighCoins)
            }
            else -> {
                ShowProgress()
            }
        }
    }
}

@Composable
fun HomeAppBar() {
    TopAppBar(modifier = Modifier.fillMaxWidth()) {
        Box(Modifier.height(32.dp)) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_z_cash),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }

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