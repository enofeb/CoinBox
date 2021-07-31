package com.enofeb.core.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enofeb.core.state.UiState
import com.enofeb.core.state.UiStateRender
import com.enofeb.core.state.intent.UiIntent

abstract class BaseFragment<UI : UiIntent, US : UiState, VM : BaseViewModel<UI, US>>(val viewModelClass: Class<VM>) :
    Fragment(),
    UiStateRender<US> {

    private val _viewModel by lazy {
        ViewModelProvider(this).get(viewModelClass.kotlin.java)
    }
}
