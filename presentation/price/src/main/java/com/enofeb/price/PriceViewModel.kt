package com.enofeb.price

import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.price.PriceRepository
import com.enofeb.core.extensions.io
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(private val priceRepository: PriceRepository) :
    BaseViewModel<PriceIntent>() {

    override fun handleIntent(intent: PriceIntent) {
        when (intent) {
            is PriceIntent.LoadingIntent -> {
                //no-op
            }
            is PriceIntent.LoadCurrencyIntent -> {

            }
            else -> {
                //no-op
            }
        }
    }

}