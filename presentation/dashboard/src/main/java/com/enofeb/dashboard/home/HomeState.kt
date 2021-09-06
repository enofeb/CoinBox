package com.enofeb.dashboard.home

import com.enofeb.core.data.market.coin.Coin

data class HomeState(
    val hotCoins: List<Coin>? = emptyList(),
    val gainCoins: List<Coin>? = emptyList(),
    val loserCoins: List<Coin>? = emptyList(),
    val todayHighCoins: List<Coin>? = emptyList(),
    val isLoading: Boolean? = false
)