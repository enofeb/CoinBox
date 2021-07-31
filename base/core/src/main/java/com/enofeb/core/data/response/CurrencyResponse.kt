package com.enofeb.core.data.response

import com.enofeb.core.data.price.Currency
import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("data") val currencyList: List<Currency>?
)