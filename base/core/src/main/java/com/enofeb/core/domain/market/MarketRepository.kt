package com.enofeb.core.domain.market

import com.enofeb.core.data.price.Coin
import com.enofeb.core.domain.Repository
import kotlinx.coroutines.flow.Flow

interface MarketRepository : Repository {
    fun getCoinMarket(): Flow<List<Coin>?>
}