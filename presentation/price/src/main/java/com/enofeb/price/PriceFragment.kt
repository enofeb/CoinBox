package com.enofeb.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceFragment :
    BaseFragment<PriceIntent, PriceUiState, PriceViewModel>(PriceViewModel::class.java) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Text("This is price fragment!")
        }
        triggerIntent(PriceIntent.FetchCurrencyIntent)
    }

    override fun render(state: PriceUiState) {
        when (state) {
            is PriceUiState.ShowCurrency -> {
                //no-op
            }
        }
    }
}