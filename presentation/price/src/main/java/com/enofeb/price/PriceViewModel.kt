package com.enofeb.price

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.price.PriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(
    private val priceRepository: PriceRepository
) : ViewModel() {

    private val _priceUiState: MutableStateFlow<PriceUiState> =
        MutableStateFlow(PriceUiState.InitialState)

    val priceUiState: StateFlow<PriceUiState> = _priceUiState

    init {
        getExchanges()
    }

    private fun getExchanges() {
        priceRepository.getExchangeRates().onEach {
            _priceUiState.value = PriceUiState.FetchExchanges(it)
        }.launchIn(viewModelScope)
    }

}