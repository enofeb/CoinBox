package com.enofeb.dashboard.home

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.market.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val marketRepository: MarketRepository
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState.LoadingState)

    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        getMarket()
    }

    private fun getMarket() {
        marketRepository.getCoinMarket().onEach {
            _homeUiState.value = HomeUiState.ShowMarket(it)
        }.launchIn(viewModelScope)
    }

}