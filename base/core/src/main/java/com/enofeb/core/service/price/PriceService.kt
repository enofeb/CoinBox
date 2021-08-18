package com.enofeb.core.service.price

import com.enofeb.core.constants.ApiConstants.Companion.API_PREFIX
import com.enofeb.core.constants.ApiConstants.Companion.API_VERSION
import com.enofeb.core.data.price.exchange.ExchangeRateResponse
import retrofit2.http.GET

interface PriceService {

    @GET("${API_PREFIX}${API_VERSION}/exchange_rates")
    suspend fun getExchangeRates(): ExchangeRateResponse?
}