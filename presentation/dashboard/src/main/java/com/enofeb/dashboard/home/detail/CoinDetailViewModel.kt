package com.enofeb.dashboard.home.detail

import androidx.lifecycle.viewModelScope
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.market.MarketRepository
import com.enofeb.core.state.ErrorState
import com.enofeb.core.state.LoadingState
import com.enofeb.dashboard.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val marketRepository: MarketRepository
) : BaseViewModel() {

    var id: String? = null

    private val _coinDetailState = MutableStateFlow(CoinDetailState())

    val coinDetailState: StateFlow<CoinDetailState> = _coinDetailState

    init {
        getCoinDetail()
    }

    private fun getCoinDetail() {

        id?.let { coinId ->
            marketRepository.getCoinDetail(coinId).onEach {
                _coinDetailState.value = CoinDetailState(it)
                _loadingState.value = LoadingState((false))
            }.catch {
                _errorState.value = ErrorState(it.message)
            }.onStart {
                _loadingState.value = LoadingState((true))
            }.launchIn(viewModelScope)
        }
    }

}