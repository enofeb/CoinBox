package com.enofeb.core.domain.price

import com.enofeb.core.data.price.exchange.ExchangeRateResponse
import com.enofeb.core.domain.Repository
import kotlinx.coroutines.flow.Flow

interface PriceRepository : Repository {

    fun getExchangeRates(): Flow<ExchangeRateResponse?>
}