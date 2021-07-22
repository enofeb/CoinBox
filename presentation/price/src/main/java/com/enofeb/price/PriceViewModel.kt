package com.enofeb.price

import androidx.lifecycle.ViewModel
import com.enofeb.core.domain.price.PriceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(private val priceRepository: PriceRepository) :
    ViewModel() {

}