package com.enofeb.core.domain.market

import com.enofeb.core.data.market.Coin
import com.enofeb.core.data.market.popular.PopularCoinResponse
import com.enofeb.core.extensions.knock
import com.enofeb.core.service.market.MarketService
import kotlinx.coroutines.flow.Flow

class MarketRepositoryImpl(private val marketService: MarketService) : MarketRepository {

    override fun getCoinMarket(): Flow<List<Coin>?> {
        return knock {
            marketService.getCoinMarket()
        }
    }

    override fun getPopularCoins(): Flow<PopularCoinResponse?> {
        return knock {
            marketService.getPopularCoin()
        }
    }
}