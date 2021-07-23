package com.enofeb.core.base

import androidx.lifecycle.ViewModel
import com.enofeb.core.state.intent.UiIntent

abstract class BaseViewModel<UI : UiIntent> : ViewModel() {

    abstract fun handleIntent(intent: UI)
}