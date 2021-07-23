package com.enofeb.core.base

import androidx.lifecycle.ViewModel
import com.enofeb.core.state.UiState

abstract class BaseViewModel<US : UiState> : ViewModel() {

    abstract fun updateState()
}