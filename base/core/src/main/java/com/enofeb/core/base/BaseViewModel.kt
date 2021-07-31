package com.enofeb.core.base

import androidx.lifecycle.ViewModel
import com.enofeb.core.state.UiState
import com.enofeb.core.state.intent.UiIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UI : UiIntent, US : UiState> : ViewModel() {

    abstract val initialState: US

    private val _uiState: MutableStateFlow<US> by lazy { MutableStateFlow(initialState) }

    val uiState: StateFlow<US> = _uiState

    abstract fun handleIntent(intent: UI)

    fun setState(state: US) {
        _uiState.value = state
    }
}