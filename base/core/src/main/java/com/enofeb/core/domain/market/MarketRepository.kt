package com.enofeb.core.domain.market

import com.enofeb.core.data.market.coin.Coin
import com.enofeb.core.data.market.coin.CoinDetail
import com.enofeb.core.data.market.popular.PopularCoinResponse
import com.enofeb.core.domain.Repository
import kotlinx.coroutines.flow.Flow

interface MarketRepository : Repository {
    fun getCoinMarket(): Flow<List<Coin>?>

    fun getPopularCoins(): Flow<PopularCoinResponse?>

    fun getCoinDetail(id: String): Flow<CoinDetail?>
}