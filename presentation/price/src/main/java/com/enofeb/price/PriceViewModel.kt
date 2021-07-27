package com.enofeb.price

import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.price.PriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(
    private val priceRepository: PriceRepository,
    override val initialState: PriceUiState
) : BaseViewModel<PriceIntent, PriceUiState>() {

    override fun handleIntent(intent: PriceIntent) {
        when (intent) {
            is PriceIntent.LoadingIntent -> {
                //no-op
            }
            is PriceIntent.FetchCurrencyIntent -> {
                getCurrency()
            }
            else -> {
                //no-op
            }
        }
    }

    private fun getCurrency() {
        priceRepository.getCurrency().onEach {
            //no-op
        }.catch {
            //no-op
        }
    }

}