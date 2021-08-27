package com.enofeb.price

import android.util.Log
import androidx.lifecycle.*
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.di.DataModule_ProvideMarketServicesFactory
import com.enofeb.core.domain.price.PriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(
    private val priceRepository: PriceRepository
) : ViewModel() {

    private val _priceUiState: MutableStateFlow<PriceUiState> =
        MutableStateFlow(PriceUiState.InitialState)

    val priceUiState: StateFlow<PriceUiState> = _priceUiState

    private val _sellPriceState: MutableStateFlow<Double?> = MutableStateFlow(0.0)

    val sellPriceState: StateFlow<Double?> = _sellPriceState

    private val _buyPriceState: MutableStateFlow<Double?> = MutableStateFlow(0.0)

    val buyPriceState: StateFlow<Double?> = _buyPriceState

    private val _buyCurrencyState: MutableStateFlow<Double?> = MutableStateFlow(0.0)

    fun onSellPriceChange(price: Double?) {
        _sellPriceState.value = price
    }

    fun onBuyCurrency(price: Double?) {
        _buyCurrencyState.value = price
    }

    init {
        getExchanges()

        _sellPriceState.combine(_buyCurrencyState) { sellPrice, buyCurrency ->
            if (sellPrice != null) {
                _buyPriceState.value = (sellPrice * buyCurrency!!)
            }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, "")
    }

    private fun getExchanges() {
        priceRepository.getExchangeRates().onEach {
            _priceUiState.value = PriceUiState.FetchExchanges(it?.rates?.exchangeList)
        }.launchIn(viewModelScope)
    }

}