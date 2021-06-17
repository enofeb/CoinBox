package com.enofeb.core.service.price

import com.enofeb.core.constants.ApiConstants.Companion.API_PREFIX
import com.enofeb.core.data.response.CurrencyResponse
import retrofit2.http.GET

interface PriceInterface {

    @GET("${API_PREFIX}/currencies")
    suspend fun getCurrencies(): CurrencyResponse?
}