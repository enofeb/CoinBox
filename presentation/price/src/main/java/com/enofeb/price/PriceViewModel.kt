package com.enofeb.price

import androidx.lifecycle.viewModelScope
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.price.PriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(
    private val priceRepository: PriceRepository
) : BaseViewModel<PriceIntent, PriceUiState>() {

    override val initialState: PriceUiState
        get() = PriceUiState.InitialState

    override fun handleIntent(intent: PriceIntent) {
        when (intent) {
            is PriceIntent.LoadingIntent -> {
                setState(PriceUiState.LoadingState)
            }
            is PriceIntent.FetchCoinsIntent -> {
                getCoinList()
            }
            else -> {
                //no-op
            }
        }
    }

    private fun getCoinList() {
        priceRepository.getCurrency().onEach {
            setState(PriceUiState.ShowCoins(it))
        }.launchIn(viewModelScope)
    }

}