package com.enofeb.price

import com.enofeb.core.data.market.Coin
import com.enofeb.core.state.UiState

sealed class PriceUiState : UiState {

    object InitialState : PriceUiState()

    object LoadingState : PriceUiState()

    data class ShowCoins(val coins: List<Coin>?) : PriceUiState()
}