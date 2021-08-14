package com.enofeb.core.domain.price

import com.enofeb.core.data.price.Coin
import com.enofeb.core.domain.Repository
import kotlinx.coroutines.flow.Flow

interface PriceRepository : Repository {

    fun getCoinMarket(): Flow<List<Coin>?>
}