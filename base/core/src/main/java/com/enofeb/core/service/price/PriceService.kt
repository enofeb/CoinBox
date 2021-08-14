package com.enofeb.core.service.price

import com.enofeb.core.constants.ApiConstants.Companion.API_PREFIX
import com.enofeb.core.constants.ApiConstants.Companion.API_VERSION
import com.enofeb.core.data.price.Coin
import com.enofeb.core.data.price.CurrencyType
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface PriceService {

    @GET("${API_PREFIX}${API_VERSION}/coins/markets")
    suspend fun getCoinMarket(@Query("vs_currency") vsCurrency: String? = CurrencyType.USD.shortName): List<Coin>?
}