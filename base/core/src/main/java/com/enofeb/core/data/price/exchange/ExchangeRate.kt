package com.enofeb.core.data.price.exchange

import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    @SerializedName("name") val name: String?,
    @SerializedName("unit") val unit: String,
    @SerializedName("value") val value: Double?,
    @SerializedName("type") val type: String?
)