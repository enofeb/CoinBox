package com.enofeb.core.domain.price

import com.enofeb.core.data.price.Coin
import com.enofeb.core.extensions.knock
import com.enofeb.core.service.price.PriceService
import kotlinx.coroutines.flow.Flow

class PriceRepositoryImpl(private val priceService: PriceService) : PriceRepository {

    override fun getCoinMarket(): Flow<List<Coin>?> {
        return knock {
            priceService.getCoinMarket()
        }
    }
}