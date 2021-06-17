package com.enofeb.core.domain.currency

import com.enofeb.core.data.response.CurrencyResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getServices(): Flow<CurrencyResponse>
}