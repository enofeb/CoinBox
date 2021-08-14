package com.enofeb.dashboard.home

import com.enofeb.core.data.price.Coin
import com.enofeb.core.state.UiState

sealed class HomeUiState : UiState {

    object InitialState : HomeUiState()

    object LoadingState : HomeUiState()

    data class ShowMarket(val coins: List<Coin>?) : HomeUiState()
}