package com.enofeb.dashboard.home

import com.enofeb.core.data.market.Coin

data class HomeState(val coins: List<Coin>? = emptyList(), val isLoading: Boolean? = false)