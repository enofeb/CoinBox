package com.enofeb.dashboard.home.detail

import com.enofeb.core.base.BaseViewModel
import com.enofeb.core.domain.market.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val marketRepository: MarketRepository
) : BaseViewModel() {


}