package com.enofeb.dashboard.home

import androidx.lifecycle.viewModelScope
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.price.PriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val priceRepository: PriceRepository
) : BaseViewModel<HomeIntent, HomeUiState>() {

    override val initialState: HomeUiState
        get() = HomeUiState.InitialState

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadingIntent -> {
                setState(HomeUiState.LoadingState)
            }
            is HomeIntent.FetchMarketIntent -> {
                getMarket()
            }
            else -> {
                //no-op
            }
        }
    }

    private fun getMarket() {
        priceRepository.getCoinMarket().onEach {
            setState(HomeUiState.ShowMarket(it))
        }.launchIn(viewModelScope)
    }

}