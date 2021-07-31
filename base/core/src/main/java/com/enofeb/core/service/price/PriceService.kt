package com.enofeb.core.service.price

import com.enofeb.core.constants.ApiConstants.Companion.API_PREFIX
import com.enofeb.core.constants.ApiConstants.Companion.API_VERSION
import com.enofeb.core.data.price.Coin
import retrofit2.http.GET

interface PriceService {

    @GET("${API_PREFIX}${API_VERSION}/coins/list")
    suspend fun getCoinList(): List<Coin>?
}