package com.enofeb.core.domain.market

import com.enofeb.core.data.price.Coin
import com.enofeb.core.extensions.knock
import com.enofeb.core.service.market.MarketService
import kotlinx.coroutines.flow.Flow

class MarketRepositoryImpl(private val marketService: MarketService) : MarketRepository {
    override fun getCoinMarket(): Flow<List<Coin>?> {
        return knock {
            marketService.getCoinMarket()
        }
    }
}