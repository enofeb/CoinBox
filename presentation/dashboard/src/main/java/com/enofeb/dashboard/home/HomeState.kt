package com.enofeb.dashboard.home

import com.enofeb.core.data.market.Coin

data class HomeState(
    val hotCoins: List<Coin>? = emptyList(),
    val popularCoins: List<Coin>? = emptyList(),
    val isLoading: Boolean? = false
)