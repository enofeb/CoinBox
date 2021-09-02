package com.enofeb.price

import com.enofeb.core.data.price.exchange.ExchangeRate

sealed class PriceUiState {

    object InitialState : PriceUiState()

    object LoadingState : PriceUiState()

    data class FetchExchanges(val exchanges: MutableList<ExchangeRate>?) : PriceUiState()
}