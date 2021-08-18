package com.enofeb.core.data.price.exchange

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(
    @SerializedName("rates") val rates: ExchangeRateCollection?
)