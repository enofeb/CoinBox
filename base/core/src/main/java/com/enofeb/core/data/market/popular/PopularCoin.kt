package com.enofeb.core.data.market.popular

import com.google.gson.annotations.SerializedName

data class PopularCoin(
    @SerializedName("item") val item: PopularCoinItem
)