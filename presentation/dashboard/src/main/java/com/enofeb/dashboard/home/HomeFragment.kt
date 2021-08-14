package com.enofeb.dashboard.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeIntent, HomeUiState, HomeViewModel>(HomeViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        triggerIntent(HomeIntent.FetchMarketIntent)
    }

    @Composable
    override fun Render(state: HomeUiState) {
        when (state) {
            is HomeUiState.ShowMarket -> {
                Log.e("ELLO", state.coins.toString())
            }
            is HomeUiState.LoadingState -> {
                //no-op
            }
            else -> {
                //no-op
            }
        }
    }

    @Composable
    override fun DrawScreen() {
        //no-op
    }

}