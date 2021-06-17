package com.enofeb.core.domain.price

import com.enofeb.core.data.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow

interface PriceRepository {

    fun getCurrency(): Flow<CurrencyResponse>
}