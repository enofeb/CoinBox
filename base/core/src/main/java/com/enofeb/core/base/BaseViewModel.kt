package com.enofeb.core.base

import androidx.lifecycle.ViewModel
import com.enofeb.core.state.ErrorState
import com.enofeb.core.state.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _loadingState = MutableStateFlow(LoadingState(false))

    val loadingState: StateFlow<LoadingState> = _loadingState

    protected val _errorState = MutableStateFlow(ErrorState())

    val errorState: StateFlow<ErrorState> = _errorState
}