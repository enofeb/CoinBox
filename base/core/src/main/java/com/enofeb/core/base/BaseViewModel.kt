package com.enofeb.core.base

import androidx.lifecycle.ViewModel
import com.enofeb.core.state.UiState
import com.enofeb.core.state.intent.UiIntent

abstract class BaseViewModel<UI : UiIntent, US : UiState> : ViewModel() {

    abstract fun handleIntent(intent: UI)
}