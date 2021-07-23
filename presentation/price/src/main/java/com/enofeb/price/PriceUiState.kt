package com.enofeb.price

import com.enofeb.core.state.UiState

sealed class PriceUiState : UiState {

    object LoadingState : PriceUiState()
}
