package com.enofeb.price

import androidx.lifecycle.ViewModel
import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.price.PriceRepository
import com.enofeb.core.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(private val priceRepository: PriceRepository) :
    BaseViewModel<PriceUiState>() {

    override fun updateState() {
        TODO("Not yet implemented")
    }

}