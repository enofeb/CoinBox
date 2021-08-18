package com.enofeb.price

import com.enofeb.core.state.intent.UiIntent

sealed class PriceIntent : UiIntent {

    object InitialIntent : PriceIntent()

    object LoadingIntent : PriceIntent()

    object FetchExchangesIntent : PriceIntent()

    data class ErrorState(val t: Throwable) : PriceIntent()
}
