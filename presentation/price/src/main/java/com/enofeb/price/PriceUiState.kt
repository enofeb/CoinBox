package com.enofeb.price

import com.enofeb.core.data.market.Coin
import com.enofeb.core.data.price.exchange.ExchangeRate
import com.enofeb.core.data.price.exchange.ExchangeRateResponse
import com.enofeb.core.state.UiState

sealed class PriceUiState : UiState {

    object InitialState : PriceUiState()

    object LoadingState : PriceUiState()

    data class FetchExchanges(val exchanges: MutableList<ExchangeRate>?) : PriceUiState()
}