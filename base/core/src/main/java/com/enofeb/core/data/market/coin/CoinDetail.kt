package com.enofeb.core.data.market.coin

import com.google.gson.annotations.SerializedName

data class CoinDetail(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image:CoinImage
)