package com.enofeb.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.data.market.Coin
import com.enofeb.core.domain.market.MarketRepository
import com.enofeb.core.state.ErrorState
import com.enofeb.core.state.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val marketRepository: MarketRepository
) : BaseViewModel() {

    private val _homeUiState = MutableStateFlow(HomeState())

    val homeUiState: StateFlow<HomeState> = _homeUiState

    init {
        viewModelScope.launch {
            combine(
                marketRepository.getCoinMarket(),
                marketRepository.getPopularCoins()
            ) { hotCoins, popularCoins ->
                HomeState(hotCoins, popularCoins?.coins?.map {
                    it.item.let { popularCoin ->
                        Coin(
                            id = popularCoin.id,
                            symbol = popularCoin.symbol,
                            name = popularCoin.name,
                            imageUrl = popularCoin.imageUrl,
                            currentPrice = popularCoin.priceBtc
                        )
                    }
                }
                )
            }.onEach {
                _homeUiState.value = it
                _loadingState.value = LoadingState(false)
            }.catch {
                _errorState.value = ErrorState(it.message)
            }.onStart {
                _loadingState.value = LoadingState(true)
            }.collect()
        }
    }

}