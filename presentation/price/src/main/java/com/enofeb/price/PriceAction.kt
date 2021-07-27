package com.enofeb.price

import com.enofeb.core.state.action.UiAction

sealed class PriceAction : UiAction {

    object FetchCurrency : PriceAction()
}