package com.enofeb.core.data.price

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("min_size") val minSize: String?
)