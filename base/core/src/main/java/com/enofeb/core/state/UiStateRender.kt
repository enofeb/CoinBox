package com.enofeb.core.state

interface UiStateRender<UI : UiState> {
    fun render(state: UI)
}