package com.enofeb.core.domain.price

import com.enofeb.core.data.response.CurrencyResponse
import com.enofeb.core.domain.Repository
import kotlinx.coroutines.flow.Flow

interface PriceRepository : Repository {

    fun getCurrency(): Flow<CurrencyResponse>
}