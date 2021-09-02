package com.enofeb.price

import com.enofeb.core.data.price.exchange.ExchangeRate


data class PriceState(val exchanges: MutableList<ExchangeRate>? = mutableListOf())