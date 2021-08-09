package com.enofeb.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.enofeb.core.state.UiState
import com.enofeb.core.state.UiStateRender
import com.enofeb.core.state.intent.UiIntent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
            ObserveDemo()
        }
    }

    @Composable
    private fun ObserveDemo() {
        val viewState = viewModel.uiState.collectAsState().value
        Render(viewState)
    }

    fun triggerIntent(intent: UI) {
        viewModel.handleIntent(intent)
    }

}
