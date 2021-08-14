package com.enofeb.dashboard.home

import androidx.compose.runtime.Composable
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeIntent, HomeUiState, HomeViewModel>(HomeViewModel::class.java) {

    @Composable
    override fun Render(state: HomeUiState) {
        when (state) {
            is HomeUiState.ShowMarket -> {

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