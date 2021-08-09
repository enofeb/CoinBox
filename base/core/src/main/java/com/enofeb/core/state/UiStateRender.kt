package com.enofeb.core.state

import androidx.compose.runtime.Composable

interface UiStateRender<UI : UiState> {
    @Composable
    fun Render(state: UI)
}