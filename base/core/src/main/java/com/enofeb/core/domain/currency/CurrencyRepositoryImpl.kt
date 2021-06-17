package com.enofeb.core.domain.currency

import com.enofeb.core.data.response.CurrencyResponse
import com.enofeb.core.service.price.PriceService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepositoryImpl(private val priceService: PriceService) : CurrencyRepository {

    override fun getServices(): Flow<CurrencyResponse>? {
        return flow {
            emit(priceService.getCurrencies())

        }
    }
}