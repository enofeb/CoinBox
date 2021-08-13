package com.enofeb.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enofeb.core.state.UiState
import com.enofeb.core.state.UiStateRender
import com.enofeb.core.state.intent.UiIntent
import com.enofeb.core.ui.theme.CoinBoxTheme

abstract class BaseFragment<UI : UiIntent, US : UiState, VM : BaseViewModel<UI, US>>(private val viewModelClass: Class<VM>) :
    Fragment(),
    UiStateRender<US> {

    private val viewModel by lazy {
        ViewModelProvider(this).get(viewModelClass.kotlin.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ComposeMagic {
                DrawScreen()
                ObserveDemo()
            }
        }
    }


    @Composable
    private fun ObserveDemo() {
        val viewState = viewModel.uiState.collectAsState().value
        Render(viewState)
    }

    @Composable
    private fun ComposeMagic(content: @Composable () -> Unit) {
        CoinBoxTheme(darkTheme = true) {
            Surface(color = Color.Black) {
                content()
            }
        }
    }

    @Composable
    abstract fun DrawScreen()

    fun triggerIntent(intent: UI) {
        viewModel.handleIntent(intent)
    }

}
