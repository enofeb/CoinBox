package com.enofeb.core.domain.market

import com.enofeb.core.data.market.Coin
import com.enofeb.core.data.market.popular.PopularCoinResponse
import com.enofeb.core.domain.Repository
import kotlinx.coroutines.flow.Flow

interface MarketRepository : Repository {
    fun getCoinMarket(): Flow<List<Coin>?>

    fun getPopularCoins(): Flow<PopularCoinResponse?>
}