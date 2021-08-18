package com.enofeb.price

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.enofeb.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enofeb.core.data.market.Coin

@AndroidEntryPoint
class PriceFragment :
    BaseFragment<PriceIntent, PriceUiState, PriceViewModel>(PriceViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        triggerIntent(PriceIntent.FetchExchangesIntent)
    }

    @Composable
    override fun Render(state: PriceUiState) {
        when (state) {
            is PriceUiState.FetchExchanges -> {
            }
            is PriceUiState.LoadingState -> {
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

@Preview
@Composable
fun ItemPreview() {
    Row {
        Text(text = "Etherium")
    }
}