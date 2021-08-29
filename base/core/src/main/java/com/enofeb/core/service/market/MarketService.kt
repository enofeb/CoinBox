package com.enofeb.core.service.market

import com.enofeb.core.constants.ApiConstants
import com.enofeb.core.data.market.Coin
import com.enofeb.core.data.market.CurrencyType
import com.enofeb.core.data.market.popular.PopularCoinResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketService {

    @GET("${ApiConstants.API_PREFIX}${ApiConstants.API_VERSION}/coins/markets")
    suspend fun getCoinMarket(@Query("vs_currency") vsCurrency: String? = CurrencyType.USD.shortName): List<Coin>?

    @GET("${ApiConstants.API_PREFIX}${ApiConstants.API_VERSION}/search/trending")
    suspend fun getPopularCoin(): PopularCoinResponse
}