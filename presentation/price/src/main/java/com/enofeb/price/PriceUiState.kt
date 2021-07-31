package com.enofeb.price

import com.enofeb.core.data.price.Currency
import com.enofeb.core.state.UiState

sealed class PriceUiState : UiState {

    object InitialState : PriceUiState()

    data class ShowCurrency(val currencyList: List<Currency>?) : PriceUiState()
}