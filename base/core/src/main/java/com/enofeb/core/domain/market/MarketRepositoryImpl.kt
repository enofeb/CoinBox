package com.enofeb.core.domain.market

import com.enofeb.core.data.market.coin.Coin
import com.enofeb.core.data.market.coin.CoinDetail
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

    override fun getCoinDetail(id: String): Flow<CoinDetail?> {
        return knock {
            marketService.getCoinDetail(id)
        }
    }
}