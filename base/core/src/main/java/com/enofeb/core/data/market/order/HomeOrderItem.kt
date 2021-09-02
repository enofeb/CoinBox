package com.enofeb.core.data.market.order

sealed class HomeOrderItem(
    var title: String
) {

    object Hot : HomeOrderItem("Hot")

    object Gainers : HomeOrderItem("Gainers")

    object Losers : HomeOrderItem("Losers")

    object LastDayHighers : HomeOrderItem("24h")
}