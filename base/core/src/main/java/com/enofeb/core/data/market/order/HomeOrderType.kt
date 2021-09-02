package com.enofeb.core.data.market.order

enum class HomeOrderType(
    var title: String
) {
    HOT("Hot"), GAINER("Gainers"), LOSERS("Losers"), TODAYHIGH("24h")
}