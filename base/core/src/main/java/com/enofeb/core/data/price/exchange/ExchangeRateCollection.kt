package com.enofeb.core.data.price.exchange

import com.google.gson.annotations.SerializedName

data class ExchangeRateCollection(
    @SerializedName("btc") val bitcoin: ExchangeRate,
    @SerializedName("eth") val etherium: ExchangeRate,
    @SerializedName("xrp") val ripple: ExchangeRate,
) {
    val exchangeList: MutableList<ExchangeRate>
        get() = mutableListOf(bitcoin, etherium, ripple)
}