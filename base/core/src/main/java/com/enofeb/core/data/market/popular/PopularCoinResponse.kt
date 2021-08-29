package com.enofeb.core.data.market.popular

import com.google.gson.annotations.SerializedName

data class PopularCoinResponse(
    @SerializedName("coins") val coins: List<PopularCoin>
)