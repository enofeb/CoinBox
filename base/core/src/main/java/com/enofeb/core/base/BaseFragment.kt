package com.enofeb.core.base

import android.os.Bundle
import android.view.View
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

    private val _viewModel by lazy {
        ViewModelProvider(this).get(viewModelClass.kotlin.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                _viewModel.uiState.collect {
                    render(it)
                }
            }
        }
    }

    fun triggerIntent(intent: UI) {
        _viewModel.handleIntent(intent)
    }

}
