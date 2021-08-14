package com.enofeb.core.data.market

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("current_price") val currentPrice: Double
)