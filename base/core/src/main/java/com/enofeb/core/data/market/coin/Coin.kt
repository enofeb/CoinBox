package com.enofeb.core.data.market.coin

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("price_change_percentage_24h") val changePercentage: Double,
    @SerializedName("high_24h") val todayMaxPrice: Double,
    @SerializedName("total_volume") val volume: Long,
)