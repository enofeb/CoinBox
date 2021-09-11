package com.enofeb.dashboard.home

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.compose.rememberImagePainter
import com.enofeb.core.base.BaseFragment
import com.enofeb.core.data.market.coin.Coin
import com.enofeb.core.data.market.order.HomeOrderType
import com.enofeb.core.extensions.addPercentage
import com.enofeb.core.extensions.addVolPrefix
import com.enofeb.core.extensions.formatNumber
import com.enofeb.core.extensions.roundOffDecimal
import com.enofeb.core.ui.dimens.DefaultPadding
import com.enofeb.core.ui.dimens.SmallPadding
import com.enofeb.core.ui.theme.MindGreen
import com.enofeb.dashboard.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.pager.*

private val CoinImageSize = 10.dp
private val CoinElevation = 10.dp
private val ToolbarHeight = 32.dp

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
                CoinScreen(viewModel) {
                    val bundle = Bundle().apply {
                        this.putString(COIN_ID, it)
                    }
                    findNavController().navigate(R.id.action_home_to_coinDetail, bundle)
                }
            }
        }
    }

    companion object {
        const val COIN_ID = "coin_id"
    }

}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CoinScreen(viewModel: HomeViewModel, onItemClick: (String) -> Unit) {

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

    Scaffold(topBar = { HomeAppBar() }) {
        Column {
            MarketOrderTabs(listOrderTypes, pagerState)
            MarketOrderTabsContent(
                pagerState,
                state.hotCoins,
                state.gainCoins,
                state.loserCoins,
                state.todayHighCoins,
                onItemClick,
                loadingState.isLoading
            )
        }
    }

}

@Composable
fun CoinList(coins: List<Coin>?, onItemClick: (String) -> Unit) {
    coins?.let { list ->
        Column(Modifier.fillMaxSize()) {
            LazyColumn {
                items(
                    items = list,
                    itemContent = { CoinItem(coin = it, onItemClick) })
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinItem(coin: Coin, onItemClick: (String) -> Unit) {
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(DefaultPadding), elevation = CoinElevation,
        backgroundColor = Color.Black,
        onClick = { onItemClick.invoke(coin.id) }
    ) {
        Row(
            modifier = Modifier.padding(DefaultPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberImagePainter(coin.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(DefaultPadding, DefaultPadding)
                        .align(Alignment.CenterVertically)
                )
                Column(Modifier.padding(start = SmallPadding)) {
                    Text(
                        text = coin.symbol.uppercase(),
                        color = Color.White
                    )
                    Text(
                        text = coin.volume.formatNumber().addVolPrefix(),
                        color = Color.LightGray,
                        fontSize = 10.sp
                    )
                }
            }
            Text(
                text = coin.currentPrice.toString(),
                color = Color.White, fontSize = 14.sp
            )
            PercentageCard(
                coin.changePercentage.roundOffDecimal().addPercentage(),
                if (coin.changePercentage > 0) {
                    MindGreen
                } else {
                    Color.Red
                }
            )
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
    onItemClick: (String) -> Unit,
    isLoading: Boolean?
) {

    if (isLoading == true) {
        ShowProgress()
    }

    HorizontalPager(state = pagerState) { page ->
        when (page) {
            HomeOrderType.HOT.ordinal -> {
                CoinList(hotCoins, onItemClick)
            }
            HomeOrderType.GAINER.ordinal -> {
                CoinList(gainerCoins, onItemClick)
            }
            HomeOrderType.LOSERS.ordinal -> {
                CoinList(loserCoins, onItemClick)
            }
            HomeOrderType.TODAYHIGH.ordinal -> {
                CoinList(todayHighCoins, onItemClick)
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
        Box(Modifier.height(ToolbarHeight)) {
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

@Composable
fun PercentageCard(value: String, color: Color) {
    Card(
        backgroundColor = color
    ) {
        Text(
            text = value,
            color = Color.White,
            modifier = Modifier.padding(SmallPadding),
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun ItemPreview() {
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(DefaultPadding), elevation = CoinElevation,
        backgroundColor = Color.Black
    ) {
        Row(
            modifier = Modifier.padding(DefaultPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberImagePainter("https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880"),
                    contentDescription = null,
                    Modifier.size(CoinImageSize, CoinImageSize)
                )
                Column {
                    Text(text = "ETH", color = Color.White)
                    Text(text = "Vol 32M", color = Color.LightGray, fontSize = 10.sp)
                }
            }
            Text(text = "3224.5", color = Color.White)
            Card(
                backgroundColor = MindGreen
            ) {
                Text(
                    text = "+%1.20",
                    color = Color.White,
                    modifier = Modifier.padding(SmallPadding),
                    fontSize = 14.sp
                )
            }
        }
    }
}