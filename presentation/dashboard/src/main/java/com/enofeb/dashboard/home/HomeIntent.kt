package com.enofeb.dashboard.home

import com.enofeb.core.state.intent.UiIntent

sealed class HomeIntent : UiIntent {

    object InitialIntent : HomeIntent()

    object LoadingIntent : HomeIntent()

    object FetchMarketIntent : HomeIntent()
}