package com.enofeb.core.domain.price

import com.enofeb.core.data.price.exchange.ExchangeRateResponse
import com.enofeb.core.extensions.knock
import com.enofeb.core.service.price.PriceService
import kotlinx.coroutines.flow.Flow

class PriceRepositoryImpl(private val priceService: PriceService) : PriceRepository {

    override fun getExchangeRates(): Flow<ExchangeRateResponse?> {
        return knock {
            priceService.getExchangeRates()
        }
    }
}