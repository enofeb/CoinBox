package com.enofeb.core.domain.price

import com.enofeb.core.data.response.CurrencyResponse
import com.enofeb.core.extensions.knock
import com.enofeb.core.service.price.PriceService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PriceRepositoryImpl(private val priceService: PriceService) : PriceRepository {

    override fun getCurrency(): Flow<CurrencyResponse?> {
        return knock {
            priceService.getCurrencies()
        }
    }
}