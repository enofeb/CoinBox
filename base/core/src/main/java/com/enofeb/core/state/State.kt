package com.enofeb.core.state

data class LoadingState(val isLoading: Boolean?)

data class ErrorState(val message: String? = null)