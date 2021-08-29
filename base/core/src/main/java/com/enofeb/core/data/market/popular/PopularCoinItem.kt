package com.enofeb.core.data.market.popular

import com.google.gson.annotations.SerializedName

data class PopularCoinItem(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("large") val imageUrl: String,
    @SerializedName("price_btc") val priceBtc: Double
)