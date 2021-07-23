package com.enofeb.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun ViewModel.io(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch { block() }
}